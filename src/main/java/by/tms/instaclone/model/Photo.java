package by.tms.instaclone.model;

import by.tms.instaclone.settings.TimeZoneSettings;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Photo {
    private final UUID uuid;
    private final Post post;
    private final byte[] image;
    private final LocalDateTime createAt;

    public Photo(Post post,byte[] image) {
        this.uuid = UUID.randomUUID();
        this.post = post;
        this.image = image;
        this.createAt = LocalDateTime.now(TimeZoneSettings.getUtcClock());
    }
    public Photo(UUID uuid,Post post,byte[] image,LocalDateTime createAt) {
        this.uuid = uuid;
        this.post = post;
        this.image = image;
        this.createAt = createAt;
    }

    public LocalDateTime getCreateAt() {          //Метод возвращающий время создания сущности
        return createAt;
    }

    public UUID getUuid() {                        //Метод для возвращающий UUID сущности Фото
        return uuid;
    }

    public Post getPost() {                        //Метод для возвращающий Post которому принадлежит фото
        return post;
    }

    public byte[] getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(uuid, photo.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }
}