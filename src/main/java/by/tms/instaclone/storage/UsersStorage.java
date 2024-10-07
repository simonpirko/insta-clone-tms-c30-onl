package by.tms.instaclone.storage;

import by.tms.instaclone.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static by.tms.instaclone.storage.Deleter.deleteContentCsvFile;
import static by.tms.instaclone.storage.KeeperConstants.*;
import static by.tms.instaclone.storage.Reader.readCsvFile;
import static by.tms.instaclone.storage.Writer.writeCsvFile;

/** Объект класса хранит все объекты класса User (далее Пользователи)
 *  реализован как класс-одиночка
 */
public class UsersStorage {
    private static UsersStorage usersStorage;
    private ConcurrentHashMap<UUID, User> users;

    public static synchronized UsersStorage getInstance() {
        if (usersStorage == null) {
            usersStorage = new UsersStorage();
        }
        return usersStorage;
    }

    public ConcurrentHashMap<UUID, User> getUsers() {
        return users;
    }

    /**
     * Метод создаёт нового Пользователя и сохраняет его в USERS_CSV_FILE
     * @param name      - имя Пользователя
     * @param username  - логин Пользователя
     * @param password  - пароль Пользователя
     * @return          - новый Пользователя
     */
    public User newUser(String name, String username, String password) {
        User user = new User(name, username, password);
        users.put(user.getUuid(), user);
        UsernamesStorage.getInstance().newUser(user);
        // todo: с переходом к БД - сделать как с Объектом
        String rowText = USERS_CSV_FORMAT_TEMPLATE.formatted(user.getUuid().toString(), user.getName(), user.getUsername(),
                user.getPassword(), user.getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()/1000);
        writeCsvFile(USERS_CSV_FILE, rowText);
        return user;
    }

    public void newUser(User user) {
        users.put(user.getUuid(), user);
        UsernamesStorage.getInstance().newUser(user);
        // todo: с переходом к БД - сделать как с Объектом
        String rowText = USERS_CSV_FORMAT_TEMPLATE.formatted(user.getUuid().toString(), user.getName(), user.getUsername(),
                user.getPassword(), user.getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()/1000);
        writeCsvFile(USERS_CSV_FILE, rowText);
    }

    /**
     * Метод меняет для указанного Пользователя его User.name
     * @param user      - Пользователя, у которого производится замена name
     * @param newName   - новое name
     */
    public void changeName(User user, String newName) {
        User newUser = users.get(user.getUuid());
        newUser.setName(newName);
        substitute(user, newUser);
    }

    /**
     * Метод меняет для указанного Пользователя его User.username
     * @param user          - Пользователя, у которого производится замена username
     * @param newUsername   - новый username
     */
    public void changeUsername(User user, String newUsername) {
        User newUser = users.get(user.getUuid());
        newUser.setUsername(newUsername);
        substitute(user, newUser);
    }

    /**
     * Метод меняет для указанного Пользователя его User.password
     * @param user          - Пользователя, у которого производится замена password
     * @param newPassword   - новый password
     */
    public void changePassword(User user, String newPassword) {
        User newUser = users.get(user.getUuid());
        newUser.setPassword(newPassword);
        substitute(user, newUser);
    }

    /**
     * Метод возвращает по указанному UUID Пользователя с таким UUID
     * @param uuid  - UUID-Пользователя
     * @return      - объект-Пользователь
     */
    public User getUser(UUID uuid) {
        return users.get(uuid);
    }

    /**
     * Метод возвращает User'а по указанному username
     * @param username  - username Пользователя
     * @return      - объект-Пользователь
     */
    public User getUser(String username) {
        return users.get(UsernamesStorage.getInstance().getUUID(username));
    }

    /**
     * Метод удаляет переданного Пользователя
     * Пользователь удаляется из Storage и USERS_CSV_FILE (см. deleteContentCsvFile())
     * @param user
     */
    public void deleteUser(User user) {
        UsernamesStorage.getInstance().deleteUser(usersStorage.getUser(user.getUuid()).getUsername());
        PostsStorage.getInstance().deletePostOwner(user);
        deleteHeirs(user);
        users.remove(user.getUuid());
        rewrite();
    }

    private UsersStorage() {
        users = new ConcurrentHashMap<>();
        Optional<String> fileString = readCsvFile(USERS_CSV_FILE);
        if (fileString.get().length() > 0) {
            String[] arrayRows = fileString.get().split(LF);
            for (String row : arrayRows) {
                String[] arrayWords = row.split(SEPARATOR_CSV);
                users.put(UUID.fromString(arrayWords[0]), new User(UUID.fromString(arrayWords[0]), arrayWords[1], arrayWords[2], arrayWords[3],
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(arrayWords[4])), ZoneId.systemDefault())));
            }
        }
    }

    private void rewrite() {
        StringBuilder contentUsersStorage = new StringBuilder();
        for (Map.Entry entry: users.entrySet()) {
            contentUsersStorage.append(((User) entry.getValue()).getUuid().toString()).append(SEPARATOR_CSV)
                    .append(((User) entry.getValue()).getName()).append(SEPARATOR_CSV)
                    .append(((User) entry.getValue()).getUsername()).append(SEPARATOR_CSV)
                    .append(((User) entry.getValue()).getPassword()).append(SEPARATOR_CSV)
                    .append(((User) entry.getValue()).getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()/1000).append(SEPARATOR_CSV)
                    .append(LF);
        }
        deleteContentCsvFile(USERS_CSV_FILE);
        writeCsvFile(USERS_CSV_FILE, contentUsersStorage.toString());
    }

    private void deleteHeirs(User user) {
        PostsStorage.getInstance().deletePostOwner(user);
        SubscriptionsStorage.getInstance().deleteSubscriptionFollower(user);
        SubscriptionsStorage.getInstance().deleteSubscriptionPublisher(user);
    }

    private void substitute(User oldUser, User newUser) {
        deleteUser(oldUser);
        newUser(newUser);
    }
}