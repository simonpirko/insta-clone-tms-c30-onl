package by.tms.instaclone;

import by.tms.instaclone.storage.UsernamesStorage;
import by.tms.instaclone.storage.UsersStorage;
import by.tms.instaclone.model.User;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UsersStorageProbe {
    public static void main(String[] args) {
// работа с Логером
//        getLogger().addRecord(ERROR_TEMPLATE.formatted(ERROR_IO_FILE_TEMPLATE.formatted(LOGS_FILE)));

// работа со Storage
        User user3 = UsersStorage.getInstance().getUser("alex");    // отдать User'а по username
        UsersStorage.getInstance().newUser("Ivan", "login", "password");
//        User user = usersStorage.getUser(uuidUser);
        User user2 = UsersStorage.getInstance().getUser(UsernamesStorage.getInstance().getUUID("alex"));
        int i = 0;
    }
}
