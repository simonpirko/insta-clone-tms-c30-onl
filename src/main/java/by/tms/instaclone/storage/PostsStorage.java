package by.tms.instaclone.storage;

import by.tms.instaclone.model.*;
import by.tms.instaclone.utilites.CommentsComparator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static by.tms.instaclone.storage.Deleter.deleteContentCsvFile;
import static by.tms.instaclone.storage.KeeperConstants.*;
import static by.tms.instaclone.storage.KeeperConstants.SEPARATOR_CSV;
import static by.tms.instaclone.storage.Reader.readCsvFile;
import static by.tms.instaclone.storage.Writer.writeCsvFile;

/**
 * Объект класса хранит все объекты класса Post
 * реализован как класс-одиночка
 */
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

    /**
     * Метод создаёт новый Post от имени ownerUser с текстом textPost и сохраняет её в POSTS_CSV_FILE
     *
     * @param ownerUser - объект-владелец поста
     * @param textPost  - текст поста
     * @return
     */
    public Post newPost(User ownerUser, String textPost) {
        Post post = new Post(ownerUser, textPost);
        // todo: с переходом к БД - сделать как с Объектом
        String rowText = POSTS_CSV_FORMAT_TEMPLATE.formatted(post.getUuid().toString(), post.getOwner().getUuid().toString(),
                post.getText(), post.getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() / 1000);
        writeCsvFile(POSTS_CSV_FILE, rowText);
        post.setCreateAt();
        posts.put(post.getUuid(), post);
        return post;
    }

    private void newPost(Post post) {
        posts.put(post.getUuid(), post);
        // todo: с переходом к БД - сделать как с Объектом
        String rowText = POSTS_CSV_FORMAT_TEMPLATE.formatted(post.getUuid().toString(), post.getOwner().getUuid().toString(),
                post.getText(), post.getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() / 1000);
        writeCsvFile(POSTS_CSV_FILE, rowText);
    }

    /**
     * Метод удаляет указанный Post
     *
     * @param post - объект-Post
     */
    public void deletePost(Post post) {
        deleteHeirs(post);
        posts.remove(post.getUuid());
        rewrite();
    }

    /**
     * Метод удаляет все посты указанного владельца из хранилища и POSTS_CSV_FILE (см. deletePost())
     *
     * @param owner - объект-владелец постов
     */
    public void deletePostOwner(User owner) {
        for (Map.Entry entry : posts.entrySet()) {
            if (((Post) entry.getValue()).getOwner().equals(owner)) {
                deleteHeirs((Post) entry.getValue());
                posts.remove(entry.getKey());
            }
        }
        rewrite();
    }

    /**
     * Метод возращает объет-пост по его UUID
     *
     * @param uuid - UUID поста
     * @return - объект поста
     */
    public Post getPost(UUID uuid) {
        return posts.get(uuid);
    }

    /**
     * Метод возвращает набор постов их владельца по UUID объекта-владельца
     *
     * @param ownerUuid - UUID объекта-владельца
     * @return - набор постов
     */
    public HashMap<UUID, Post> getPostsOwner(UUID ownerUuid) {
        HashMap<UUID, Post> postsOwner = new HashMap<>();
        for (Map.Entry entry : posts.entrySet()) {
            if (((Post) entry.getValue()).getOwner().getUuid().equals(ownerUuid)) {
                postsOwner.put(((Post) entry.getValue()).getUuid(), (Post) entry.getValue());
            }
        }
        return postsOwner;
    }

    /**
     * Метод возвращает набор постов по их владельцу
     *
     * @param owner - объект-владелец
     * @return - набор постов
     */
    public HashMap<UUID, Post> getPostsOwner(User owner) {
        HashMap<UUID, Post> postsOwner = new HashMap<>();
        for (Map.Entry entry : posts.entrySet()) {
            if (((Post) entry.getValue()).getOwner().equals(owner)) {
                postsOwner.put(((Post) entry.getValue()).getUuid(), (Post) entry.getValue());
            }
        }
        return postsOwner;
    }

    public Post getHotPostOwner(User owner) {
        HashMap<UUID, Post> postsOwner = getPostsOwner(owner);
        LocalDateTime maxDateTime = LocalDateTime.of(1970, 3, 8, 8, 0, 0, 0);
        Post hotPost = null;
        for (Post post : postsOwner.values()) {
            if (post.getCreateAt().isAfter(maxDateTime)) {
                hotPost = post;
            }
        }
        return hotPost;
    }

    /**
     * Метод возращает последний Post Владельца по UUID Владельца
     *
     * @param ownerUuid - UUID Владельца
     * @return - Post, последний по дате публикации или NULL, если постов нет совсем
     */
    public List<Post> getLastPostOwner(UUID ownerUuid) {
        HashMap<UUID, Post> postsOwner = getPostsOwner(UsersStorage.getInstance().getUser(ownerUuid));
        LocalDateTime maxDateTime = LocalDateTime.of(1970, 3, 8, 8, 0, 0, 0);
        List<Post> posts = new ArrayList<>();
        Post lastPost = null;
        for (Post post : postsOwner.values()) {
            if (post.getCreateAt().isAfter(maxDateTime)) {
                lastPost = post;
                maxDateTime = post.getCreateAt();
            }
        }
        if (lastPost != null) {
            posts.add(lastPost);
        }
        return posts;
    }

    /**
     * Метод возращает Posts Владельца по UUID Владельца
     *
     * @param ownerUuid - UUID Владельца
     * @return - List<Post>, все посты User, если постов нет совсем вернёт пустой List
     */
    public List<Post> getListPostsOwner(UUID ownerUuid) {
        HashMap<UUID, Post> postsOwner = getPostsOwner(UsersStorage.getInstance().getUser(ownerUuid));
        List<Post> posts = new ArrayList<>();
        for (Post post : postsOwner.values()) {
            posts.add(post);
        }
        return posts;
    }


    /**
     * Метод меняет текст в переданном посте
     *
     * @param post    - объект-пост, в котором меняется текст
     * @param newText - новый текст
     */
    public void changeText(Post post, String newText) {
        Post newPost = posts.get(post.getUuid());
        newPost.setText(newText);
        substitute(post, newPost);
    }

    private PostsStorage() {
        posts = new ConcurrentHashMap<>();
        Optional<String> fileString = readCsvFile(POSTS_CSV_FILE);
        if (fileString.get().length() > 0) {
            String[] arrayRows = fileString.get().split(LF);
            for (String row : arrayRows) {
                String[] arrayWords = row.split(SEPARATOR_CSV);
                posts.put(UUID.fromString(arrayWords[0]), new Post(UUID.fromString(arrayWords[0]),
                        UsersStorage.getInstance().getUser(UUID.fromString(arrayWords[1])), arrayWords[2],
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(arrayWords[3])), ZoneId.systemDefault())));
            }
        }
    }

    private void rewrite() {
        StringBuilder contentPostsStorage = new StringBuilder();
        for (Map.Entry entry : posts.entrySet()) {
            contentPostsStorage.append(((Post) entry.getValue()).getUuid().toString()).append(SEPARATOR_CSV)
                    .append(((Post) entry.getValue()).getOwner().getUuid().toString()).append(SEPARATOR_CSV)
                    .append(((Post) entry.getValue()).getText()).append(SEPARATOR_CSV)
                    .append(((Post) entry.getValue()).getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() / 1000).append(SEPARATOR_CSV)
                    .append(LF);
        }
        deleteContentCsvFile(POSTS_CSV_FILE);
        writeCsvFile(POSTS_CSV_FILE, contentPostsStorage.toString());
    }

    private void deleteHeirs(Post post) {
        CommentsStorage commentsStorage = CommentsStorage.getInstance();
        List<Comment> comments = commentsStorage.getAllCommentsPost(post.getUuid());
        for(Comment comment : comments) {
            commentsStorage.deleteComment(comment);
        }
        ReactionsStorage reactionsStorage = ReactionsStorage.getInstance();
        List<Reaction> reactions = reactionsStorage.getAllReactionPost(post.getUuid());
        for(Reaction reaction : reactions) {
            reactionsStorage.deleteReaction(reaction);
        }
        PhotoStorage photoStorage = PhotoStorage.getInstance();
        List<Photo> photoList = photoStorage.getAllPhotosPost(post.getUuid());
        System.out.println(photoList);
        for(Photo photo : photoList) {
            photoStorage.deletePhoto(photo);
        }
    }

    private void substitute(Post oldPost, Post newPost) {
        deletePost(oldPost);
        newPost(newPost);
    }
}