package by.tms.instaclone.keepers.servants;

import by.tms.instaclone.keepers.interfaces.Storage;
import by.tms.instaclone.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Объект класса является "Одиночкой" и предназначен для хранения User'ов
 */
public class OldUsersStorage implements Storage {
    private static OldUsersStorage usersStorage;
    private static List<User> users;

    public static synchronized OldUsersStorage getUsersStorage() {
        if (usersStorage == null) {
            usersStorage = new OldUsersStorage();
        }
        return usersStorage;
    }

    private OldUsersStorage() {
        users = new ArrayList<>();
    }

    @Override
    public synchronized void appendStorage(Object object) {
        if (object instanceof User user) {
            if (!users.contains(user)) users.add(user);
        }
    }

    @Override
    public synchronized void removeStorage(Object object) {
        if (object instanceof User user) {
            users.remove(user);
        }
    }

    @Override
    public synchronized boolean containsStorage(Object object) {
        for (User user : users) {
            if (user.equals(object)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<?> getStorage() {
        return new ArrayList<>(users);
    }

    @Override
    public Optional<Object> getEntity(UUID uuid) {
        for (User user : users) {
            if (user.getUuid().equals(uuid)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}