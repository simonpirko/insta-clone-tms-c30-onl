package by.tms.instaclone.storage;

import by.tms.instaclone.model.User;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

// Класс является "производной" от UsersStorage и служит для быстрого определения уникальности username'а
// реализован как класс-Одиночка
public class UsernamesStorage {
    private static UsernamesStorage usernamesStorage;
    private ConcurrentHashMap<String, String> usernames;

    public static synchronized UsernamesStorage getInstance() {
        if (usernamesStorage == null) {
            usernamesStorage = new UsernamesStorage();
        }
        return usernamesStorage;
    }

    public ConcurrentHashMap<String, String> getUsernames() {
        return usernames;
    }

    public void newUser(User user) {
        usernames.put(user.getUsername(), "");
    }

    public void deleteUser(String username) {
        usernames.remove(username);
    }

    private UsernamesStorage() {
        usernames = new ConcurrentHashMap<>();
        ConcurrentHashMap<UUID, User> users = UsersStorage.getInstance().getUsers();
        for (Map.Entry entry: users.entrySet()) {
            usernames.put(((User) entry.getValue()).getUsername(), "");
        }
    }
}
