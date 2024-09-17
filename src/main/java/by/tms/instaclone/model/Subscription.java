package by.tms.instaclone.model;

import by.tms.instaclone.settings.TimeZoneSettings;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Subscription {

    //userUuid: идентификатор Пользователя- к которому относятся рекомендации
    //userList: список рекомендованных пользователей

    private final UUID uuid;// идентификатор Подписки
    private final User subscriber;//  подписавшейся Пользователь
    private final User subscription;//  Пользователь на кого подписался
    private final LocalDateTime createAt;// время создания Подписки

    public Subscription(UUID uuid, User subscriber, User subscription) {
        this.uuid = uuid;
        this.subscriber = subscriber;
        this.subscription = subscription;
        this.createAt = LocalDateTime.now(TimeZoneSettings.getUtcClock());
    }

    public UUID getUuid() {
        return uuid;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public User getSubscription() {
        return subscription;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }
}
