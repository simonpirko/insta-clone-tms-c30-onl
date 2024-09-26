package by.tms.instaclone;

import by.tms.instaclone.model.Post;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.UsernamesStorage;
import by.tms.instaclone.storage.UsersStorage;
import by.tms.instaclone.model.User;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AlexGaragProbe {
    public static void main(String[] args) {
// работа с Логером
//        getLogger().addRecord(ERROR_TEMPLATE.formatted(ERROR_IO_FILE_TEMPLATE.formatted(LOGS_FILE)));
// работа со Storage
        UsersStorage usersStorage = UsersStorage.getInstance();
        ConcurrentHashMap<UUID, User> users1 = usersStorage.getUsers();
        ConcurrentHashMap<UUID, User> users2 = UsersStorage.getInstance().getUsers();
        ConcurrentHashMap<String, User> usernames1 = UsernamesStorage.getInstance().getUsernames();
        ConcurrentHashMap<String, User> usernames2 = UsernamesStorage.getInstance().getUsernames();

        String name = "Nom1";
        String username = "nom1";
        String password = "nom1";
        if (usernames1.get(username) == null) {
            User user = new User(name, username, password);
            usersStorage.newUser(user);
        } else {
            System.out.println("Username already exists");
        }

        User userAtom = usersStorage.getUser(UUID.fromString("3e10f8c8-0924-3d3a-8f94-c18e7addb866"));

        PostsStorage postsStorage = PostsStorage.getInstance();
        ConcurrentHashMap<UUID, Post> posts1 = postsStorage.getPosts();
        ConcurrentHashMap<UUID, Post> posts2 = postsStorage.getPosts();

        Post post = postsStorage.getPost(UUID.fromString("4270310a-1309-4f64-b823-99bfc55a240b"));

        Map<UUID, Post> posts3 = postsStorage.getPostsOwner(UUID.fromString("3e10f8c8-0924-3d3a-8f94-c18e7addb866"));
        Map<UUID, Post> posts4 = postsStorage.getPostsOwner(userAtom.getUuid());




        int i = 0;


    }

}
