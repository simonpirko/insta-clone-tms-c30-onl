package by.tms.instaclone;

import by.tms.instaclone.model.Photo;
import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.PhotoStorage;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.UsernamesStorage;
import by.tms.instaclone.storage.UsersStorage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static by.tms.instaclone.storage.KeeperConstants.SLAGE;
import static by.tms.instaclone.storage.KeeperConstants.USER_PROFILE_URL;

public class PostsStorageProbe {
    public static void main(String[] args) {
// работа со Storage
        User owner = UsersStorage.getInstance().getUser(UUID.fromString("029ad4c2-5c5c-309d-b578-604976d65aa1")); // Rome1


        Post post = PostsStorage.getInstance().getPost(UUID.fromString("d03249b1-e9ab-454b-9d52-900b96c4690e"));
        List<String> photosPosts = new ArrayList<>(PhotoStorage.getInstance().getPhotosPost(post.getUuid()));


        int i = 0;
    }
}
