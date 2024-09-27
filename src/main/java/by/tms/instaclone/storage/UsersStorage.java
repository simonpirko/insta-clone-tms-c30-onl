package by.tms.instaclone.storage;

import by.tms.instaclone.model.Post;
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

// реализован как класс-Одиночка
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

    public void changeName(User user, String newName) {
        User newUser = users.get(user.getUuid());
        newUser.setName(newName);
        substitute(user, newUser);
    }

    public void changeUsername(User user, String newUsername) {
        User newUser = users.get(user.getUuid());
        newUser.setUsername(newUsername);
        substitute(user, newUser);
    }

    public void changePassword(User user, String newPassword) {
        User newUser = users.get(user.getUuid());
        newUser.setPassword(newPassword);
        substitute(user, newUser);
    }

    public User getUser(UUID uuid) {
        return users.get(uuid);
    }

    public void newUser(User user) {
        users.put(user.getUuid(), user);
        UsernamesStorage.getInstance().newUser(user);
        // todo: с переходом к БД - сделать как с Объектом
        String rowText = USERS_CSV_FORMAT_TEMPLATE.formatted(user.getUuid().toString(), user.getName(), user.getUsername(),
                user.getPassword(), user.getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()/1000);
        writeCsvFile(USERS_CSV_FILE, rowText);
    }

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
            String[] arrayRows = fileString.get().split(LF);   // делим csv-файл на строки по LF ("перевод каретки")
            for (String row : arrayRows) {
                String[] arrayWords = row.split(SEPARATOR_CSV);   // делим строку на "слова" по SEPARATOR_CSV
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
        PostsStorage postsStorage = PostsStorage.getInstance();
        postsStorage.deletePostOwner(user);
        // todo удалить Reaction's на Post
        // todo удалить Subscription's на User
        // todo удалить фото Post'а
    }

    private void substitute(User oldUser, User newUser) {
        deleteUser(oldUser);
        newUser(newUser);
    }
}