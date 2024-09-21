package by.tms.instaclone.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class User {
//    private static User user;
    private final UUID uuid;                // ИД User - генерация через ЛОГИН и по UUID и сравниваем User
    private String name;                    // имя-фамилия / имя-второе имя-фамилия / "кличка" etc.
    private final String username;          // логин
    private String password;                // пароль
    private final LocalDateTime createAt;   // время создания User
    // private String profile; //ссылка на профиль пользователя

//    public static synchronized User makeUser(UUID uuid, String name, String username, String password, LocalDateTime createAt) {
//        if (user == null) {
//            return new User(name, username, password);
//        } else if (user.getUsername().equals(username)) {
//            return user;
//        } else {
//            return new User(uuid, name, username, password, createAt);
//        }
//    }

    /**
     * Создание нового User
     *
     * @param name
     * @param username
     * @param password
     */
    public User(String name, String username, String password) {
        this.uuid = UUID.nameUUIDFromBytes(username.getBytes()); // моё предложение для увеличения вероятности уникальности
        this.name = name;
        this.username = username;
        this.password = password;
        this.createAt = LocalDateTime.now();
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

//    private static Builder builder(String name, String username, String password) {
//        return new Builder(name, username, password);
//    }
//
//    private static class Builder {
//        private UUID uuid;
//        private String name;
//        private final String username;
//        private String password;
//        private LocalDateTime createAt;
//
//        public Builder(String name, String username, String password) {
//            this.name = name;
//            this.username = username;
//            this.password = password;
//        }
//
//        public Builder uuid(UUID uuid) {
//            this.uuid = uuid;
//            return this;
//        }
//
//        public Builder createAt(LocalDateTime createAt) {
//            this.createAt = createAt;
//            return this;
//        }
//
//        public User build() {
//            return new User(uuid, name, username, password, createAt);
//        }
//    }

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

    public void setPassword(String password) {
        this.password = password;
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
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}