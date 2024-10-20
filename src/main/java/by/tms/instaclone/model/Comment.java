package by.tms.instaclone.model;

import by.tms.instaclone.settings.TimeZoneSettings;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Comment {
    private final UUID uuid;// *** идентификатор комментария
    private final Post addressee;//  комментируемый пост
    private final User owner;//  пользователь-комментатор поста
    private String text;// текст комментария
    private LocalDateTime createAt;// время создания Комментария

    public Comment(Post postUuid, User owner, String text) {
        this.addressee = postUuid;
        this.owner = owner;
        this.text = text;
        this.uuid = UUID.randomUUID();
        this.createAt = LocalDateTime.now(TimeZoneSettings.getUtcClock());
    }

    public Comment(UUID uuid, Post postUuid, User owner, String text, LocalDateTime createAt) {
        this.uuid = uuid;
        this.addressee = postUuid;
        this.owner = owner;
        this.text = text;
        this.createAt = createAt;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Post getAddressee() {
        return addressee;
    }

    public User getOwner() {
        return owner;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreateAt() {
        this.createAt = LocalDateTime.now();
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
