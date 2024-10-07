package by.tms.instaclone.storage;

import by.tms.instaclone.model.User;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/** Класс является "производной" от UsersStorage и выполняет роль квази-индекса UsersStorage по username
 * c его помощью легко определить уникальность User.username
 * реализован как класс-Одиночка*/
public class UsernamesStorage {
    private static UsernamesStorage usernamesStorage;
    private ConcurrentHashMap<String, UUID> usernames;

    public static synchronized UsernamesStorage getInstance() {
        if (usernamesStorage == null) {
            usernamesStorage = new UsernamesStorage();
        }
        return usernamesStorage;
    }

    public ConcurrentHashMap<String, UUID> getUsernames() {
        return usernames;
    }

    public void newUser(User user) {
        usernames.put(user.getUsername(), user.getUuid());
    }

    /**
     * Метод возвращает объект UUID User'а по его username
     * @param username  - username User'а, чей UUID хотим получить
     * @return          - найденный UUID
     */
    public UUID getUUID(String username) {
        return usernames.get(username);
    }

    public void deleteUser(String username) {
        usernames.remove(username);
    }

    private UsernamesStorage() {
        usernames = new ConcurrentHashMap<>();
        ConcurrentHashMap<UUID, User> users = UsersStorage.getInstance().getUsers();
        for (Map.Entry entry: users.entrySet()) {
            usernames.put(((User) entry.getValue()).getUsername(), ((User) entry.getValue()).getUuid());
        }
    }
}