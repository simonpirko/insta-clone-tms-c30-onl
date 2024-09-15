package by.tms.instaclone.model;

import java.time.Instant;
import java.time.InstantSource;
import java.util.Objects;
import java.util.UUID;

public class Comment {
    private final String uuid;// *** идентификатор  комментария
    private final String postUuid;// идентификатор комментируемого поста
    private final String userUuid;// идентификатор пользователя-комментатора поста
    private String text;// текст комментария
    private long createAt;// время создания Комментария в секундах с 1970 года

    public Comment(String postUuid, String userUuid, String text) {
        this.postUuid = postUuid;
        this.userUuid = userUuid;
        this.text = text;
        this.uuid = String.valueOf(UUID.randomUUID());
        InstantSource testingSource = InstantSource.fixed(Instant.now());
        this.createAt = testingSource.instant().getEpochSecond();
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

    public long getCreateAt() {
        return createAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreateAt() {
        InstantSource testingSource = InstantSource.fixed(Instant.now());
        this.createAt = testingSource.instant().getEpochSecond();
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
