package by.tms.instaclone;

import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.UsernamesStorage;
import by.tms.instaclone.storage.UsersStorage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static by.tms.instaclone.storage.KeeperConstants.SLAGE;
import static by.tms.instaclone.storage.KeeperConstants.USER_PROFILE_URL;

public class PostsStorageProbe {
    public static void main(String[] args) {
// работа со Storage
        User owner = UsersStorage.getInstance().getUser(UUID.fromString("029ad4c2-5c5c-309d-b578-604976d65aa1")); // Rome1


        UUID uuidPublisher = UsernamesStorage.getInstance().getUUID(owner.getUsername());
        String userNamePublisher = UsersStorage.getInstance().getUser(uuidPublisher).getUsername();
        String namePublisher = UsersStorage.getInstance().getUser(uuidPublisher).getName();
        String urlPublisher = USER_PROFILE_URL + SLAGE + userNamePublisher;
        List<Post> lastPostsPublisher = new ArrayList<>(PostsStorage.getInstance().getLastPostOwner(uuidPublisher));
        List<String> textLastPostPublisher = new ArrayList<>();
        List<LocalDateTime> createAtLastPost = new ArrayList<>();
        for (Post post : lastPostsPublisher) {
            textLastPostPublisher.add(post.getText());
            createAtLastPost.add(post.getCreateAt());
        }

//        PostsStorage postsStorage = PostsStorage.getInstance();
//        Post deletePost = postsStorage.getPost(UUID.fromString("916af874-3314-48c2-9f51-7246ccecd141"));
//        postsStorage.deletePost(deletePost);

//        HashMap<UUID, Post> posts = PostsStorage.getInstance().getPostsOwner(owner.getUuid());
//
//        Post hotPost = postsStorage.getHotPostOwner(owner);

//        postsStorage.changeText(post, "Заменённый ТЕКСТ");


        int i = 0;
    }
}
