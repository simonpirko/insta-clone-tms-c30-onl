package by.tms.instaclone.model;

import by.tms.instaclone.settings.TimeZoneSettings;

import java.time.LocalDateTime;
import java.util.UUID;

public class Photo {
    private final UUID uuid;                     //*** идентификатор фото
    private final Post post;                     //пост которому принадлежит фото
    private final byte[] image;
    private final String format;
    private final LocalDateTime createAt;        // время загрузки фото

    public Photo(Post post,byte[] image,String format) {                   //конструктор для класса
        this.uuid = UUID.randomUUID();          //генерируем рандомный UUID
        this.post = post;                       //сохраняем Пост которому принаджежит фото
        this.image = image;
        this.format = format;
        this.createAt = LocalDateTime.now(TimeZoneSettings.getUtcClock()); //Сохраянем время создания сущности
    }

    public LocalDateTime getCreateAt() {          //Метод возвращающий время создания сущности
        return createAt;
    }
    public UUID getUuid() {                        //Метод для возвращающий UUID сущности Фото
        return uuid;
    }
    public Post getPost() {                        //Метод для пвозвращающий Post которому принадлежит фото
        return post;
    }
    public byte[] getImage() {
        return image;
    }
    public String getFormat() {
        return format;
    }
}
