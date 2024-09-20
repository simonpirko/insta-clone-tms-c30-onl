package by.tms.instaclone.model;

import by.tms.instaclone.settings.TimeZoneSettings;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class User {

    // private String profile; //ссылка на профиль пользователя

    private final UUID uuid;// идентификатор Пользователя
    private String name; //имя и фамилия
    private String username; //******* логин (по нему и сравниваем пользователей)
    private String password; //пароль
    private final LocalDateTime createAt; //время создания User


    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.uuid =UUID.nameUUIDFromBytes(username.getBytes()); // моё предложение для увеличения вероятности уникальности
        this.createAt = LocalDateTime.now(TimeZoneSettings.getUtcClock());
    }
// конструктор необходим для создания сушности User из файла (БД)
    public User(UUID uuid, String name, String username, String password, LocalDateTime createAt) {
        this.uuid = uuid;
        this.name = name;
        this.username = username;
        this.password = password;
        this.createAt = createAt;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createAt=" + createAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}

