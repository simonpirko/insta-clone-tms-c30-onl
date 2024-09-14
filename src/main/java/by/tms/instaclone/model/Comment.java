package by.tms.instaclone.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class Comment {
    private final String uuid;// идентификатор  комментария
    private final String postUuid;// идентификатор комментируемого поста
    private final String userUuid;// идентификатор пользователя-комментатора поста
    private String text;// текст комментария
    private String createAt;// время создания comment в формате dd-MM-yyyy HH:mm:ss

    public Comment(String postUuid, String userUuid, String text) {
        this.postUuid = postUuid;
        this.userUuid = userUuid;
        this.text = text;
        this.uuid = String.valueOf(UUID.randomUUID());
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.createAt = dateTime.format(formatter);
    }

    public String getUuid() {
        return uuid;
    }

    public String getPostUuid() {
        return postUuid;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public String getCreateAt() {
        return createAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreateAt() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.createAt = dateTime.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(uuid, comment.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }
}
