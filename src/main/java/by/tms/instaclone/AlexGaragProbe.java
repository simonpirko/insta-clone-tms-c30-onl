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

        String name = "Rome1";
        String username = "rome1";
        String password = "rome1";
        if (usernames1.get(username) == null) {
            User user = new User(name, username, password);
            usersStorage.newUser(user);
        } else {
            System.out.println("Username already exists");
        }
// работа с Post'ами
        PostsStorage postsStorage = PostsStorage.getInstance();
        ConcurrentHashMap<UUID, Post> posts1 = postsStorage.getPosts();
        ConcurrentHashMap<UUID, Post> posts2 = postsStorage.getPosts();

        Post post = postsStorage.getPost(UUID.fromString("4270310a-1309-4f64-b823-99bfc55a240b"));
        String text = post.getText();

        Map<UUID, Post> posts3 = postsStorage.getPostsOwner(UUID.fromString("3e10f8c8-0924-3d3a-8f94-c18e7addb866"));

        User userNom = usersStorage.getUser(UUID.fromString("aee37c30-f5d0-31a4-9552-6f636a3527bb"));
        postsStorage.newPost(new Post(userNom, "пост Nom'а - 1"));
        Map<UUID, Post> posts4 = postsStorage.getPostsOwner(userNom.getUuid());


        int i = 0;


    }

}
