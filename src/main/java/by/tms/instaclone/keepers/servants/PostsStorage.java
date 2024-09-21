package by.tms.instaclone.keepers.servants;

import by.tms.instaclone.keepers.interfaces.Storage;
import by.tms.instaclone.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Объект класса является "Одиночкой" и предназначен для хранения Post'ов
 */
public class PostsStorage implements Storage {
    private static PostsStorage postsStorage;
    private static List<Post> posts;

    public static synchronized PostsStorage getPostsStorage() {
        if (postsStorage == null) {
            postsStorage = new PostsStorage();
        }
        return postsStorage;
    }

    private PostsStorage() {
        posts = new ArrayList<>();
    }

    @Override
    public synchronized void appendStorage(Object object) {
        if (object instanceof Post user) {
            if (!posts.contains(user)) posts.add(user);
        }
    }

    @Override
    public synchronized void removeStorage(Object object) {
        if (object instanceof Post user) {
            posts.remove(user);
        }
    }

    @Override
    public synchronized boolean containsStorage(Object object) {
        for (Post user : posts) {
            if (user.equals(object)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<?> getStorage() {
        return new ArrayList<>(posts);
    }

    @Override
    public Optional<Object> getEntity(UUID uuid) {
        for (Post post : posts) {
            if (post.getUuid().equals(uuid)) {
                return Optional.of(post);
            }
        }
        return Optional.empty();
    }
}