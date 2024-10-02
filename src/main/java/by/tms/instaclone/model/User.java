package by.tms.instaclone.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class User {
//    private static User user;
    private final UUID uuid;                // ИД User - генерация через ЛОГИН и по UUID и сравниваем User
    private String name;                    // имя-фамилия / имя-второе имя-фамилия / "кличка" etc.
    private String username;                // логин
    private String password;                // пароль
    private final LocalDateTime createAt;   // время создания User

    /**
     * Создание нового User
     *
     * @param name
     * @param username
     * @param password
     */
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.createAt = LocalDateTime.now();
        this.uuid = UUID.nameUUIDFromBytes((username + this.createAt).getBytes()); // моё предложение для увеличения вероятности уникальности
    }

    /**
     * Пересоздание (ранее созданного/существующего) User
     *
     * @param uuid
     * @param name
     * @param username
     * @param password
     * @param createAt
     */
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

    public void setName(String newName) {
        this.name = newName;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    @Override
    public String toString() {
        return "User{uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createAt=" + createAt + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uuid, user.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }
}