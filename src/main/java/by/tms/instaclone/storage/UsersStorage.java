package by.tms.instaclone.storage;

import by.tms.instaclone.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static by.tms.instaclone.storage.KeeperConstants.*;
import static by.tms.instaclone.storage.Reader.readCsvFile;

public class UsersStorage {
    private static UsersStorage usersStorage;
    private ConcurrentHashMap<String, User> users;

    public static synchronized UsersStorage getInstance() {
        if (usersStorage == null) {
            usersStorage = new UsersStorage();
        }
        return usersStorage;
    }

    private UsersStorage() {
        users = new ConcurrentHashMap<>();
        Optional<String> fileString = readCsvFile(USERS_CSV_FILE);
        if (fileString.isPresent()) {
            String[] arrayRows = fileString.get().split(LF);   // делим csv-файл на строки по LF ("перевод каретки")
            for (String row : arrayRows) {
                String[] arrayWords = row.split(SEPARATOR_CSV);   // делим строку на "слова" по SEPARATOR_CSV
                users.put(arrayWords[0], new User(UUID.fromString(arrayWords[0]),
                        arrayWords[1],
                        arrayWords[2],
                        arrayWords[3],
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(arrayWords[4])), ZoneId.systemDefault())));
            }
        }
    }

    public ConcurrentHashMap<String, User> getUsers() {
        return users;
    }
}
