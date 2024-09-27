package by.tms.instaclone.storage;

import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static by.tms.instaclone.storage.KeeperConstants.*;
import static by.tms.instaclone.storage.KeeperConstants.SEPARATOR_CSV;
import static by.tms.instaclone.storage.Reader.readCsvFile;
import static by.tms.instaclone.storage.Writer.writeCsvFile;

public class PostsStorage {
    private static PostsStorage postsStorage;
    private ConcurrentHashMap<UUID, Post> posts;

    public static synchronized PostsStorage getInstance() {
        if (postsStorage == null) {
            postsStorage = new PostsStorage();
        }
        return postsStorage;
    }

    public ConcurrentHashMap<UUID, Post> getPosts() {
        return posts;
    }

    public void newPost(Post post) {
        posts.put(post.getUuid(), post);
        // todo: с переходом к БД - сделать как с Объектом
        String rowText = POSTS_CSV_FORMAT_TEMPLATE.formatted(post.getUuid().toString(), post.getOwner().getUuid().toString(),
                post.getText(), post.getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()/1000);
        writeCsvFile(POSTS_CSV_FILE, rowText);
    }

    public void deletePost(Post post) {
        // todo удалить комментарии на пост
        // todo удалить реакции на пост
        // todo удалить его фото
        posts.remove(post.getUuid());
        // todo: удалить его в файле (БД)
    }

    public void deletePostOwner(User owner) {
        for (Map.Entry entry: posts.entrySet()) {
            if (((Post) entry.getValue()).getOwner().equals(owner)) {
                // todo удалить комментарии на пост
                // todo удалить реакции на пост
                // todo удалить его фото
                posts.remove(entry.getKey());
            }
            // todo: удалить ВСЕ-СРАЗУ-ОДНОВРЕМЕННО! в файле (БД)
        }
    }

    public Post getPost(UUID uuid) {
        return posts.get(uuid);
    }

    public Map<UUID, Post> getPostsOwner(UUID ownerUuid) {
        Map<UUID, Post> postsOwner = new HashMap<>();
        for (Map.Entry entry: posts.entrySet()) {
            if (((Post) entry.getValue()).getOwner().getUuid().equals(ownerUuid)) {
                postsOwner.put(((Post) entry.getValue()).getUuid(), (Post) entry.getValue());
            }
        }
        return postsOwner;
    }

    public void changeText(UUID uuid, String newText) {
        //todo
    }

    // принцип работы аналогичен UsersStorage
    private PostsStorage() {
        posts = new ConcurrentHashMap<>();
        Optional<String> fileString = readCsvFile(POSTS_CSV_FILE);
        if (fileString.isPresent()) {
            String[] arrayRows = fileString.get().split(LF);
            for (String row : arrayRows) {
                String[] arrayWords = row.split(SEPARATOR_CSV);
                posts.put(UUID.fromString(arrayWords[0]), new Post(UUID.fromString(arrayWords[0]),
                        UsersStorage.getInstance().getUser(UUID.fromString(arrayWords[1])), arrayWords[2],
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(arrayWords[3])), ZoneId.systemDefault())));
            }
        }
    }

    private void substitute(Post oldPost, Post newPost) {
        deletePost(oldPost);
        newPost(newPost);
    }
}
