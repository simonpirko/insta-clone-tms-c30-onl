package by.tms.instaclone.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class Post {

    //commentList: список ссылок на оставленные Комментарии
    //likeList: список ссылок на пользователей поставивших “лайк” посту
    //image: фото поста


    private final String uuid;// идентификатор Поста
    private final String owner; // идентификатор Пользователя создателя поста (получается конструктором);
    private String text; // текст Поста
    private String createAt;// время создания Поста в формате dd-MM-yyyy HH:mm:ss

    public Post(String owner, String text) {
        this.owner = owner;
        this.text = text;
        this.uuid = String.valueOf(UUID.randomUUID());
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.createAt = dateTime.format(formatter);
    }

    public String getCreateAt() {
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
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.createAt = dateTime.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(uuid, post.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }


}
