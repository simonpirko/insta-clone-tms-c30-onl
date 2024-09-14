package by.tms.instaclone.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class Reaction {

    private final String uuid; // идентификатор Реакции
    private final String postUuid; // идентификатор оцененного Поста
    private boolean typeReaction; // есть ли лайк(true) или дизлайк(false)
    private final String reactingUuid; // uuid Пользователя-прореагировавшего
    private String createAt;// время создания reaction в формате dd-MM-yyyy HH:mm:ss


    public Reaction(String postUuid, boolean typeReaction, String reactingUuid) {
        this.postUuid = postUuid;
        this.typeReaction = typeReaction;
        this.reactingUuid = reactingUuid;
        this.uuid = String.valueOf(UUID.randomUUID());
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.createAt = dateTime.format(formatter);
    }

    public void setTypeReaction(boolean typeReaction) {
        this.typeReaction = typeReaction;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getCreateAt() {
        return createAt;
    }

    public String getReactingUuid() {
        return reactingUuid;
    }

    public boolean isTypeReaction() {
        return typeReaction;
    }

    public String getPostUuid() {
        return postUuid;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reaction reaction = (Reaction) o;
        return Objects.equals(uuid, reaction.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }
}
