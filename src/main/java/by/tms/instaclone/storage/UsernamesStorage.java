package by.tms.instaclone.storage;

import by.tms.instaclone.model.User;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

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
        // II рекомендовала так:
        users.entrySet().forEach(entry -> usernames.put((entry.getValue()).getUsername(), entry.getValue()));
//        вместо этого
//        for (Map.Entry entry: users.entrySet()) {
//            usernames.put(((User) entry.getValue()).getUsername(), (User) entry.getValue());
//        }
        int i = 0;
        // ниже - старый вариант
//        Optional<String> fileString = readCsvFile(USERS_CSV_FILE);
//        if (fileString.isPresent()) {
//            String[] arrayRows = fileString.get().split(LF);   // делим csv-файл на строки по LF ("перевод каретки")
//            for (String row : arrayRows) {
//                String[] arrayWords = row.split(SEPARATOR_CSV);   // делим строку на "слова" по SEPARATOR_CSV
//                usernames.put(arrayWords[2], new User(UUID.fromString(arrayWords[0]), arrayWords[1], arrayWords[2], arrayWords[3],
//                        LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(arrayWords[4])), ZoneId.systemDefault())));
//            }
//        }
    }
}
