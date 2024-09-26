package by.tms.instaclone;

import by.tms.instaclone.keepers.UsernamesStorage;
import by.tms.instaclone.keepers.UsersStorage;
import by.tms.instaclone.model.User;

import java.util.concurrent.ConcurrentHashMap;
//import static by.tms.instaclone.model.User.makeUser;

public class AlexGaragProbe {
    public static void main(String[] args) {
// работа с Логером
//        getLogger().addRecord(ERROR_TEMPLATE.formatted(ERROR_IO_FILE_TEMPLATE.formatted(LOGS_FILE)));
        ConcurrentHashMap<String, User> myUsers = UsersStorage.getInstance().getUsers();
        ConcurrentHashMap<String, User> myUsernames = UsernamesStorage.getInstance().getUsernames();
// тест тождественности User
//        String firstName = "Atom";
//        String firstUsername = "atom";
//        String firstPassword = "atom";
//        User firstUser = new User(firstName, firstUsername, firstPassword);
//        UsersStorage userStorage = UsersStorage.getUsersStorage();
//        userStorage.appendStorage(firstUser);
//        userStorage.appendStorage(firstUser);
//        String secondName = "Atom";
//        String secondUsername = "atom";
//        String secondPassword = "atom";
//        User secondUser = new User(secondName, secondUsername, secondPassword);
//        UsersStorage userStorage2 = UsersStorage.getUsersStorage();
//        userStorage2.appendStorage(secondUser);
//        List<?> userList = userStorage.getStorage();

// тест тождественности Post
//        String firstText = "text1";
//        Post firstPost = new Post(UUID.randomUUID(), firstUser, firstText, LocalDateTime.now());
//        String secondText2 = "text2";
//        Post secondPost = new Post(UUID.randomUUID(), firstUser, firstText, LocalDateTime.now());
//        int ii = 0;
//        Writer writer = new GeneralWriter();
////        writer.write(newUser);
//        Post newPost = new Post(newUser, "Третий пост");
//        writer.write(newPost);

// чтение из файла
//        List<?> entityRowsOwner = new ArrayList<>();
//        List<?> entityRow = new ArrayList<>();
//        List<?> users = new ArrayList<>();
//        ReaderFactory readerFactory = createReaderFactory(USERS);
//        Reader readerUsers = readerFactory.createReader();
//        users = readerUsers.read();
//        List<?> users = new ArrayList<>();
//        ReaderFactory readerFactory = createReaderFactory(USERS);
//        Reader reader = readerFactory.createReader();
//        users = reader.read();

//        List<?> posts = reader.readRow("0cdb37b3-75ab-4b65-bce6-09d3c4985578");    // node
//        entityRowsOwner = reader.readRowsOwner("0cdb37b3-75ab-4b65-bce6-09d3c4985578");     // alex, test

        int i = 0;
    }

}
