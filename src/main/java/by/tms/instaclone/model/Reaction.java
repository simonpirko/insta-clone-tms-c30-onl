package by.tms.instaclone.model;

import by.tms.instaclone.settings.TimeZoneSettings;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Reaction {

    private final UUID uuid; // идентификатор Реакции
    private final Post addressee; //  оцененный Пост
    private boolean typeReaction; // есть ли лайк(true) или дизлайк(false)
    private final User owner; // Пользователь-прореагировавший
    private LocalDateTime createAt;// время создания Реакции


    public Reaction(Post postUuid, boolean typeReaction, User owner) {
        this.addressee = postUuid;
        this.typeReaction = typeReaction;
        this.owner = owner;
        this.uuid = UUID.randomUUID();
        this.createAt = LocalDateTime.now(TimeZoneSettings.getUtcClock());
    }

    public void setTypeReaction(boolean typeReaction) {
        this.typeReaction = typeReaction;
    }

    public void setCreateAt() {
        this.createAt = LocalDateTime.now(TimeZoneSettings.getUtcClock());
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public User getReactingUuid() {
        return owner;
    }

    public boolean isTypeReaction() {
        return typeReaction;
    }

    public Post getAddressee() {
        return addressee;
    }

    public UUID getUuid() {
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
