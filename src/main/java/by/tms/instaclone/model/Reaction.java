package by.tms.instaclone.model;

import by.tms.instaclone.settings.TimeZoneSettings;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Reaction {

    private final UUID uuid; // идентификатор Реакции
    private final Post addressee; //  оцененный Пост
    private final User owner; // Пользователь-прореагировавший
    private boolean typeReaction; // есть ли лайк(true) или дизлайк(false)
    private LocalDateTime createAt;// время создания Реакции


    public Reaction(Post postUuid, User owner, boolean typeReaction) {
        this.addressee = postUuid;
        this.typeReaction = typeReaction;
        this.owner = owner;
        this.uuid = UUID.randomUUID();
        this.createAt = LocalDateTime.now(TimeZoneSettings.getUtcClock());
    }

    public Reaction(UUID uuid, Post postUuid, User owner, boolean typeReaction, LocalDateTime createAt) {
        this.uuid = uuid;
        this.addressee = postUuid;
        this.owner = owner;
        this.typeReaction = typeReaction;
        this.createAt = createAt;
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

    public boolean isTypeReaction() {
        return typeReaction;
    }

    public Post getAddressee() {
        return addressee;
    }

    public UUID getUuid() {
        return uuid;
    }

    public User getOwner() {
        return owner;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
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
