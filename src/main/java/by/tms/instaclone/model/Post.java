package by.tms.instaclone.model;

import by.tms.instaclone.settings.TimeZoneSettings;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Post {
    private final UUID uuid;
    private final User owner;
    private String text;
    private LocalDateTime createAt;

    public Post(User owner, String text) {
        this.owner = owner;
        this.text = Objects.requireNonNullElse(text, "");
        this.uuid = UUID.randomUUID();
        this.createAt = LocalDateTime.now(TimeZoneSettings.getUtcClock());
    }
    // конструктор необходим для создания Сущности Post из файла (БД),
    public Post(UUID postUUID, User owner, String text, LocalDateTime createAt) {
        this.uuid = postUUID;
        this.owner = owner;
        this.text = Objects.requireNonNullElse(text, "");
        this.createAt = createAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public User getOwner() {
        return owner;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = Objects.requireNonNullElse(text, "");
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setCreateAt() {
        this.createAt = LocalDateTime.now();
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = LocalDateTime.now(TimeZoneSettings.getUtcClock());
    }

    // todo проверить условия equals!
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
//        return Objects.equals(owner, post.owner);   // todo это условие тождественности НЕ верно!
        return Objects.equals(uuid, post.uuid);
    }

    @Override
    public String toString() {
        return "Post{uuid=" + uuid +
                ", owner=" + owner +
                ", text='" + text + '\'' +
                ", createAt=" + createAt + '}';
    }

    @Override
    public int hashCode() {
//        return Objects.hashCode(owner); // todo hash-код надо формировать по UUID
        return Objects.hashCode(getUuid());
    }
}