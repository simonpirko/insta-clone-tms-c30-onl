package by.tms.instaclone;

import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.UsernamesStorage;
import by.tms.instaclone.storage.UsersStorage;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static by.tms.instaclone.storage.KeeperConstants.*;
import static by.tms.instaclone.utilites.SiteLogger.getLogger;

public class PostsStorageProbe {
    public static void main(String[] args) {
// работа со Storage
        User owner = UsersStorage.getInstance().getUser(UUID.fromString("aa44061c-62f5-37a9-b126-43053134c2d6")); // Nom1

        PostsStorage postsStorage = PostsStorage.getInstance();
//        Post deletePost = postsStorage.getPost(UUID.fromString("916af874-3314-48c2-9f51-7246ccecd141"));
//        postsStorage.deletePost(deletePost);

        HashMap<UUID, Post> posts = PostsStorage.getInstance().getPostsOwner(owner.getUuid());

        Post hotPost = postsStorage.getHotPostPublisher(owner);

//        postsStorage.changeText(post, "Заменённый ТЕКСТ");


        int i = 0;
    }
}
