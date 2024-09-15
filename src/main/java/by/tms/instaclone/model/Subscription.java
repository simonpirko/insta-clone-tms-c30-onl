package by.tms.instaclone.model;

import java.time.Instant;
import java.time.InstantSource;
import java.util.Objects;

public class Subscription {

    //userUuid: идентификатор Пользователя- к которому относятся рекомендации
    //userList: список рекомендованных пользователей

    private final String uuid;// идентификатор Подписки
    private final String subscriberUuid;// идентификатор подписавшегося Пользователя
    private final String subscriptionUuid;// идентификатор Пользователя на кого подписался
    private final long createAt;// время создания Подписки в секундах с 1970 года

    public Subscription(String uuid, String subscriberUuid, String subscriptionUuid) {
        this.uuid = uuid;
        this.subscriberUuid = subscriberUuid;
        this.subscriptionUuid = subscriptionUuid;
        InstantSource testingSource = InstantSource.fixed(Instant.now());
        this.createAt = testingSource.instant().getEpochSecond();
    }

    public String getUuid() {
        return uuid;
    }

    public String getSubscriberUuid() {
        return subscriberUuid;
    }

    public String getSubscriptionUuid() {
        return subscriptionUuid;
    }

    public long getCreateAt() {
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
