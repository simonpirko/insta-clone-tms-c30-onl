package by.tms.instaclone;

import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.UsernamesStorage;
import by.tms.instaclone.storage.UsersStorage;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static by.tms.instaclone.storage.KeeperConstants.*;
import static by.tms.instaclone.utilites.SiteLogger.getLogger;

public class PostsStorageProbe {
    public static void main(String[] args) {
// работа с Логером
        getLogger().addRecord(ERROR_TEMPLATE.formatted(ERROR_IO_FILE_TEMPLATE.formatted(LOGS_FILE)));

// работа со Storage

        PostsStorage postsStorage = PostsStorage.getInstance();
        Post deletePost = postsStorage.getPost(UUID.fromString("916af874-3314-48c2-9f51-7246ccecd141"));
        postsStorage.deletePost(deletePost);

        UsersStorage usersStorage = UsersStorage.getInstance();
        User ownerUser = usersStorage.getUser(UUID.fromString("d1085705-5a91-383d-b1b5-c49094657e10"));
        postsStorage.deletePostOwner(ownerUser);

        Post post = postsStorage.getPost(UUID.fromString("78b0b195-f2de-42e1-8b0c-2724337c8b8d"));
        postsStorage.changeText(post, "Заменённый ТЕКСТ");


        int i = 0;
    }
}
