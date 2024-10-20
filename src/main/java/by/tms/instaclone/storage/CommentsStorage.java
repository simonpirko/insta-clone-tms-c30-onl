package by.tms.instaclone.storage;

import by.tms.instaclone.model.Comment;
import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


import static by.tms.instaclone.storage.Deleter.deleteContentCsvFile;
import static by.tms.instaclone.storage.KeeperConstants.*;
import static by.tms.instaclone.storage.Reader.readCsvFile;
import static by.tms.instaclone.storage.Writer.writeCsvFile;


public class CommentsStorage {

    private static CommentsStorage commentsStorage;
    private ConcurrentHashMap<UUID, Comment> comments;

    public static synchronized CommentsStorage getInstance() {
        if (commentsStorage == null) {
            commentsStorage = new CommentsStorage();
        }
        return commentsStorage;
    }

    private CommentsStorage() {
        comments = new ConcurrentHashMap<>();
        Optional<String> fileString = readCsvFile(COMMENTS_CSV_FILE);
        if (!fileString.get().isEmpty()) {
            String[] arrayRows = fileString.get().split(LF);
            for (String row : arrayRows) {
                String[] arrayWords = row.split(SEPARATOR_CSV);
                comments.put(UUID.fromString(arrayWords[0]), new Comment(UUID.fromString(arrayWords[0]),
                        PostsStorage.getInstance().getPost(UUID.fromString(arrayWords[1])),
                        UsersStorage.getInstance().getUser(UUID.fromString(arrayWords[2])), arrayWords[3],
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(arrayWords[4])), ZoneId.systemDefault())));
            }
        }
    }

    public ConcurrentHashMap<UUID, Comment> getComments() {
        return comments;
    }

    public Comment newComment(Post post, User user, String textComment) {
        Comment comment = new Comment(post, user, textComment);
        String rowText = COMMENTS_CSV_FORMAT_TEMPLATE.formatted(comment.getUuid().toString(),
                comment.getAddressee().getUuid().toString(),
                comment.getOwner().getUuid().toString(),
                comment.getText(),
                comment.getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()/1000);
        writeCsvFile(COMMENTS_CSV_FILE, rowText);
        comment.setCreateAt();
        comments.put(comment.getUuid(), comment);
        return comment;
    }

    private void newComment(Comment comment) {
        comments.put(comment.getUuid(), comment);
        String rowText = COMMENTS_CSV_FORMAT_TEMPLATE.formatted(comment.getUuid().toString(),
                comment.getAddressee().getUuid().toString(),
                comment.getOwner().getUuid().toString(),
                comment.getText(),
                comment.getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()/1000);
        writeCsvFile(COMMENTS_CSV_FILE, rowText);
    }

    public void deleteComment(Comment comment) {
        comments.remove(comment.getUuid());
        rewrite();
    }

    public Comment getComment(UUID uuid) {
        return comments.get(uuid);
    }

    public Map<UUID, Comment> getCommentOwner(UUID ownerUuid) {
        Map<UUID, Comment> commentOwner = new HashMap<>();
        for (Map.Entry entry: comments.entrySet()) {
            if (((Comment) entry.getValue()).getOwner().getUuid().equals(ownerUuid)) {
                commentOwner.put(((Comment) entry.getValue()).getUuid(), (Comment) entry.getValue());
            }
        }
        return commentOwner;
    }

    public List<Comment> getAllCommentsPost(UUID postUuid) {
        List<Comment> commentsPost = new ArrayList<>();
        for (Map.Entry entry: comments.entrySet()) {
            if (((Comment) entry.getValue()).getAddressee().getUuid().equals(postUuid)) {
                commentsPost.add((Comment) entry.getValue());
            }
        }
        return commentsPost;
    }

    public void changeText(Comment comment, String newText) {
        Comment newComment = comments.get(comment.getUuid());
        newComment.setText(newText);
        substitute(comment, newComment);
    }

    private void rewrite() {
        StringBuilder contentCommentsStorage = new StringBuilder();
        for (Map.Entry entry: comments.entrySet()) {
            contentCommentsStorage.append(((Comment) entry.getValue()).getUuid().toString()).append(SEPARATOR_CSV)
                    .append(((Comment) entry.getValue()).getAddressee().getUuid().toString()).append(SEPARATOR_CSV)
                    .append(((Comment) entry.getValue()).getOwner().getUuid().toString()).append(SEPARATOR_CSV)
                    .append(((Comment) entry.getValue()).getText()).append(SEPARATOR_CSV)
                    .append(((Comment) entry.getValue()).getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()/1000).append(SEPARATOR_CSV)
                    .append(LF);
        }
        deleteContentCsvFile(COMMENTS_CSV_FILE);
        writeCsvFile(COMMENTS_CSV_FILE, contentCommentsStorage.toString());
    }

    private void substitute(Comment oldComment, Comment newComment) {
        deleteComment(oldComment);
        newComment(newComment);
    }

}

