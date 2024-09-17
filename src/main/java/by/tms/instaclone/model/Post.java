package by.tms.instaclone.model;

import by.tms.instaclone.settings.TimeZoneSettings;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Post {

    //commentList: список ссылок на оставленные Комментарии
    //likeList: список ссылок на пользователей поставивших “лайк” посту
    //image: фото поста


    private final UUID uuid;// идентификатор Поста
    private final User owner; // **** Пользователь создатель поста (получается конструктором);
    private String text; // текст Поста
    private LocalDateTime createAt;// время создания Поста

    public Post(User owner, String text) {
        this.owner = owner;
        this.text = text;
        this.uuid = UUID.randomUUID();
        this.createAt = LocalDateTime.now(TimeZoneSettings.getUtcClock());
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
        this.text = text;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(owner, post.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(owner);
    }
}
