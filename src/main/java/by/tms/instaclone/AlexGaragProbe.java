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
        User ChangeNameUser = usersStorage.getUser(UUID.fromString("d1085705-5a91-383d-b1b5-c49094657e10"));
        usersStorage.changeName(ChangeNameUser, "Alex-3");
//        User deleteUser = usersStorage.getUser(UUID.fromString("029ad4c2-5c5c-309d-b578-604976d65aa1"));
//        usersStorage.deleteUser(deleteUser);
//        ConcurrentHashMap<UUID, User> users1 = usersStorage.getUsers();
//        ConcurrentHashMap<UUID, User> users2 = UsersStorage.getInstance().getUsers();
//        ConcurrentHashMap<String, String> usernames1 = UsernamesStorage.getInstance().getUsernames();
//        ConcurrentHashMap<String, String> usernames2 = UsernamesStorage.getInstance().getUsernames();
//
//
//        String name = "NewUser";
//        String username = "newuser";
//        String password = "newuser";
//        if (usernames1.get(username) == null) {
//            User user = new User(name, username, password);
//            usersStorage.newUser(user);
//        } else {
//            System.out.println("Username already exists");
//        }
// работа с Post'ами
//        PostsStorage postsStorage = PostsStorage.getInstance();
//        ConcurrentHashMap<UUID, Post> posts1 = postsStorage.getPosts();
//        ConcurrentHashMap<UUID, Post> posts2 = postsStorage.getPosts();
//
//        Post post = postsStorage.getPost(UUID.fromString("4270310a-1309-4f64-b823-99bfc55a240b"));
//        String text = post.getText();
//
//        Map<UUID, Post> posts3 = postsStorage.getPostsOwner(UUID.fromString("3e10f8c8-0924-3d3a-8f94-c18e7addb866"));
//
//        User userAlex2 = usersStorage.getUser(UUID.fromString("d1085705-5a91-383d-b1b5-c49094657e10"));
//        postsStorage.newPost(new Post(userAlex2, "новый пост - 1"));
//        Map<UUID, Post> posts4 = postsStorage.getPostsOwner(userAlex2.getUuid());
//        postsStorage.deletePostOwner(userAlex2);


        int i = 0;


    }

}
