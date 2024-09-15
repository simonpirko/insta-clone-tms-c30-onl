package by.tms.instaclone.model;

import java.time.Instant;
import java.time.InstantSource;
import java.util.Objects;
import java.util.UUID;

public class Reaction {

    private final String uuid; // идентификатор Реакции
    private final String postUuid; // идентификатор оцененного Поста
    private boolean typeReaction; // есть ли лайк(true) или дизлайк(false)
    private final String reactingUuid; // uuid Пользователя-прореагировавшего
    private long createAt;// время создания Реакции в секундах с 1970 года


    public Reaction(String postUuid, boolean typeReaction, String reactingUuid) {
        this.postUuid = postUuid;
        this.typeReaction = typeReaction;
        this.reactingUuid = reactingUuid;
        this.uuid = String.valueOf(UUID.randomUUID());
        InstantSource testingSource = InstantSource.fixed(Instant.now());
        this.createAt = testingSource.instant().getEpochSecond();
    }

    public void setTypeReaction(boolean typeReaction) {
        this.typeReaction = typeReaction;
    }

    public void setCreateAt(String createAt) {
        InstantSource testingSource = InstantSource.fixed(Instant.now());
        this.createAt = testingSource.instant().getEpochSecond();
    }

    public long getCreateAt() {
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
