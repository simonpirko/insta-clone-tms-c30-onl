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

//        User ChangeNameUser = usersStorage.getUser(UUID.fromString("91df09b5-69af-39b5-a64d-1506f50fa4bb"));
//        usersStorage.changeName(ChangeNameUser, "Proton 4");
//        usersStorage.changeUsername(ChangeNameUser, "photon 4");
//        usersStorage.changePassword(ChangeNameUser, "photon 4");
//
//        User deleteUser = usersStorage.getUser(UUID.fromString("029ad4c2-5c5c-309d-b578-604976d65aa1"));
//        usersStorage.deleteUser(deleteUser);
//
//        ConcurrentHashMap<UUID, User> users1 = usersStorage.getUsers();
//        ConcurrentHashMap<UUID, User> users2 = UsersStorage.getInstance().getUsers();
//        ConcurrentHashMap<String, UUID> usernames1 = UsernamesStorage.getInstance().getUsernames();
//
//        String name = "NewUser";
//        String username = "newuser";
//        String password = "newuser";
//        ConcurrentHashMap<String, UUID> usernames2 = UsernamesStorage.getInstance().getUsernames();
//        if (usernames1.get(username) == null) {
//            usersStorage.newUser(name, username, password); // вводим с систему нового User'а и etc.
//        } else {
//            System.out.println("Username already exists");  // действия, если User с username уже существует
//        }

        User user = usersStorage.getUser(uuidUser);
        User user2 = UsersStorage.getInstance().getUser(UsernamesStorage.getInstance().getUUID("alex"));
        int i = 0;
    }
}
