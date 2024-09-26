package by.tms.instaclone;

import by.tms.instaclone.storage.UsernamesStorage;
import by.tms.instaclone.storage.UsersStorage;
import by.tms.instaclone.model.User;

import java.util.concurrent.ConcurrentHashMap;

public class AlexGaragProbe {
    public static void main(String[] args) {
// работа с Логером
//        getLogger().addRecord(ERROR_TEMPLATE.formatted(ERROR_IO_FILE_TEMPLATE.formatted(LOGS_FILE)));
        ConcurrentHashMap<String, User> users1 = UsersStorage.getInstance().getUsers();
        ConcurrentHashMap<String, User> usernames1 = UsernamesStorage.getInstance().getUsernames();

        int i = 0;


    }

}
