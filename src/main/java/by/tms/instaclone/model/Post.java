package by.tms.instaclone.model;

import java.time.Instant;
import java.time.InstantSource;
import java.util.Objects;
import java.util.UUID;

public class Post {

    //commentList: список ссылок на оставленные Комментарии
    //likeList: список ссылок на пользователей поставивших “лайк” посту
    //image: фото поста


    private final String uuid;// идентификатор Поста
    private final String owner; // **** идентификатор Пользователя создателя поста (получается конструктором);
    private String text; // текст Поста
    private long createAt;// время создания Поста в секундах с 1970 года

    public Post(String owner, String text) {
        this.owner = owner;
        this.text = text;
        this.uuid = String.valueOf(UUID.randomUUID());
        InstantSource testingSource = InstantSource.fixed(Instant.now());
        this.createAt = testingSource.instant().getEpochSecond();
    }

    public long getCreateAt() {
        return createAt;
    }

    public String getOwner() {
        return owner;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUuid() {
        return uuid;
    }

    public void setCreateAt() {
        InstantSource testingSource = InstantSource.fixed(Instant.now());
        this.createAt = testingSource.instant().getEpochSecond();
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
