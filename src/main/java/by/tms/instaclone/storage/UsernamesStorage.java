package by.tms.instaclone.storage;

import by.tms.instaclone.model.User;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

// реализован как класс-Одиночка
public class UsernamesStorage {
    private static UsernamesStorage usernamesStorage;
    private ConcurrentHashMap<String, User> usernames;

    public static synchronized UsernamesStorage getInstance() {
        if (usernamesStorage == null) {
            usernamesStorage = new UsernamesStorage();
        }
        return usernamesStorage;
    }

    public ConcurrentHashMap<String, User> getUsernames() {
        return usernames;
    }

    public void newUser(User user) {
        usernames.put(user.getUsername(), user);
    }

    private UsernamesStorage() {
        usernames = new ConcurrentHashMap<>();
        ConcurrentHashMap<UUID, User> users = UsersStorage.getInstance().getUsers();
        for (Map.Entry entry: users.entrySet()) {
            usernames.put(((User) entry.getValue()).getUsername(), (User) entry.getValue());
        }
    }
}
