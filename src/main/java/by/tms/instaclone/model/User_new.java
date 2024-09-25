package by.tms.instaclone.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import by.tms.instaclone.settings.TimeZoneSettings;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonPropertyOrder({"uuid", "name", "username", "password", "createAt"})
public class User_new {

    private final UUID uuid;
    private String name;
    private String username;
    private String password;
    private final LocalDateTime createAt;


    public User_new(String name, String username, String password) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.username = username;
        this.password = password;
        this.createAt = LocalDateTime.now(TimeZoneSettings.getUtcClock());
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public User_new(@JsonProperty("uuid") UUID uuid,
                    @JsonProperty("name") String name,
                    @JsonProperty("username") String username,
                    @JsonProperty("password") String password,
                    @JsonProperty("createAt") LocalDateTime createAt) {
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

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createAt=" + createAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User_new user)) return false;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}