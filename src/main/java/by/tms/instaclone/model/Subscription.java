package by.tms.instaclone.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Subscription {

    //userUuid: идентификатор Пользователя- к которому относятся рекомендации
    //userList: список рекомендованных пользователей

    private final String uuid;// идентификатор Подписки
    private final String subscriberUuid;// идентификатор  подписавшегося Пользователя
    private final String subscriptionUuid;// идентификатор Пользователя на кого  подписался
    private final String createAt;// время создания Subscription в формате dd-MM-yyyy HH:mm:ss

    public Subscription(String uuid, String subscriberUuid, String subscriptionUuid) {
        this.uuid = uuid;
        this.subscriberUuid = subscriberUuid;
        this.subscriptionUuid = subscriptionUuid;
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.createAt = dateTime.format(formatter);
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

    public String getCreateAt() {
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
