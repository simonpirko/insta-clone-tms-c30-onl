package by.tms.instaclone.storage;

import by.tms.instaclone.model.Subscription;
import by.tms.instaclone.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static by.tms.instaclone.storage.Deleter.deleteContentCsvFile;
import static by.tms.instaclone.storage.KeeperConstants.*;
import static by.tms.instaclone.storage.Reader.readCsvFile;
import static by.tms.instaclone.storage.Writer.writeCsvFile;

/** Объект класса хранит все объекты класса Subscription (далее Подписка)
 *  реализован как класс-одиночка *
 */
public class SubscriptionsStorage {
    private static SubscriptionsStorage subscriptionsStorage;
    private ConcurrentHashMap<UUID, Subscription> subscriptions;

    public static synchronized SubscriptionsStorage getInstance() {
        if (subscriptionsStorage == null) {
            subscriptionsStorage = new SubscriptionsStorage();
        }
        return subscriptionsStorage;
    }

    public ConcurrentHashMap<UUID, Subscription> getSubscriptions() {
        return subscriptions;
    }

    /**
     * Метод создаёт новую Подписку между follower и publisher и сохраняет её в SUBSCRIPTIONS_CSV_FILE
     * @param follower - объект-Подписчик
     * @param publisher - объект-Публикатор (автор)
     * @return subscription - новая Подписка
     */
    public Subscription newSubscription(User follower, User publisher) {
        Subscription subscription = new Subscription(follower, publisher);
        subscriptions.put(subscription.getUuid(), subscription);
        // todo: с переходом к БД - сделать как с Объектом
        String rowText = SUBSCRIPTIONS_CSV_FORMAT_TEMPLATE.formatted(subscription.getUuid().toString(),
                subscription.getFollower().getUuid().toString(), subscription.getPublisher().getUuid().toString(),
                subscription.getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()/1000);
        writeCsvFile(SUBSCRIPTIONS_CSV_FILE, rowText);
        return subscription;
    }

    /**
     * Метод возвращает подписку по её UUID
     * @param uuid - UUID подписки
     * @return subscription - найденная Подписка
     */
    public Subscription getSubscription(UUID uuid) {
        return subscriptions.get(uuid);
    }

    /**
     * Метод возвращает все подписки на указанного Публикатора
     * @param publisher - объект-Публикатор
     * @return followers - набор объектов-Подписчиков
     */
    public HashMap<UUID, User> getFollowersPublisher(User publisher) {
        HashMap<UUID, User> followers = new HashMap<>();
        for (Map.Entry entry: subscriptions.entrySet()) {
            if (((Subscription) entry.getValue()).getPublisher().equals(publisher)) {
                followers.put((UUID) entry.getKey(), ((Subscription) entry.getValue()).getFollower());
            }
        }
        return followers;
    }

    /**
     * Метод возвращает всех Подписчиков Публикатора, указанного через его UUID
     * @param publisherUuid - UUID Публикатор
     * @return followers - List<User> (набор) объектов-Подписчиков
     */
    public List<User> getFollowersPublisher(UUID publisherUuid) {
        List<User> followers = new ArrayList<>();
        for (Map.Entry entry: subscriptions.entrySet()) {
            if (((Subscription) entry.getValue()).getPublisher().getUuid().equals(publisherUuid)) {
                followers.add(((Subscription) entry.getValue()).getFollower());
            }
        }
        return followers;
    }

    /**
     * Метод возвращает все подписки указанного Подписчика
     * @param follower - объект-Подписчик
     * @return publishers - набор объектов-Публикаторов
     */
    public HashMap<UUID, User> getPublishersFollower(User follower) {
        HashMap<UUID, User> publishers = new HashMap<>();
        for (Map.Entry entry: subscriptions.entrySet()) {
            if (((Subscription) entry.getValue()).getFollower().equals(follower)) {
                publishers.put((UUID) entry.getKey(), ((Subscription) entry.getValue()).getPublisher());
            }
        }
        return publishers;
    }

    /**
     * Метод возвращает всех Публикаторов Подписчика, указанного через его UUID
     * @param followerUuid - UUID объекта-Подписчика
     * @return publishers - List<User> (набор) объектов-Публикаторов
     */
    public List<User> getPublishersFollower(UUID followerUuid) {
        List<User> publishers = new ArrayList<>();
        for (Map.Entry entry: subscriptions.entrySet()) {
            UUID followerSubscriptionUuid = ((Subscription) entry.getValue()).getFollower().getUuid();
            if (followerSubscriptionUuid.equals(followerUuid)) {
                User publisher = ((Subscription) entry.getValue()).getPublisher();
                publishers.add(publisher);
            }
        }
        return publishers;
    }

    /**
     * Метод возвращает true/false в зависимости от того, подписан ли Подписчик на Публикатора
     * @param followerUuid - UUID объекта-Подписчика
     * @param publisherUuid - UUID объекта-Публикатора
     * @return boolean
     */
    public boolean isSubscription(UUID followerUuid, UUID publisherUuid) {
        for (Map.Entry entry: subscriptions.entrySet()) {
            UUID followerSubscriptionUuid = ((Subscription) entry.getValue()).getFollower().getUuid();
            UUID publisherSubscriptionUuid = ((Subscription) entry.getValue()).getPublisher().getUuid();
            if (followerSubscriptionUuid.equals(followerUuid)
                    && publisherSubscriptionUuid.equals(publisherUuid)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод удалаляет подписку Подписчика на Автора
     * Подписка удаляется из Storage и SUBSCRIPTIONS_CSV_FILE (см. deleteContentCsvFile())
     * followerUuid - UUID Подписчика
     * publisherUuid - UUID Автора
     */
    public void unsubscribe(UUID followerUuid, UUID publisherUuid) {
        for (Map.Entry entry: subscriptions.entrySet()) {
            UUID followerSubscriptionUuid = ((Subscription) entry.getValue()).getFollower().getUuid();
            UUID publisherSubscriptionUuid = ((Subscription) entry.getValue()).getPublisher().getUuid();
            if (followerSubscriptionUuid.equals(followerUuid)
                    && publisherSubscriptionUuid.equals(publisherUuid)) {
                deleteSubscription((Subscription) entry.getValue());
                break;
            }
        }
    }

    /**
     * Метод удалаляет переданную Подписку
     * Подписка удаляется из Storage и SUBSCRIPTIONS_CSV_FILE (см. deleteContentCsvFile())
     * @param subscription - удаляемая Подписка
     */
    public void deleteSubscription(Subscription subscription) {
        subscriptions.remove(subscription.getUuid());
        rewrite();
    }

    /**
     * Метод удалаляет все подписки объекта-Подписчика
     * @param follower - объект-Подписчик
     */
    public void deleteSubscriptionFollower(User follower) {
        for (Map.Entry entry: subscriptions.entrySet()) {
            if (((Subscription) entry.getValue()).getFollower().equals(follower)) {
                subscriptions.remove(entry.getKey());
            }
        }
        rewrite();
    }

    /**
     * Метод удалаляет все подписки на объект-Публикатор
     * @param publisher - объект-Публикатор
     */
    public void deleteSubscriptionPublisher(User publisher) {
        for (Map.Entry entry: subscriptions.entrySet()) {
            if (((Subscription) entry.getValue()).getPublisher().equals(publisher)) {
                subscriptions.remove(entry.getKey());
            }
        }
        rewrite();
    }

    private SubscriptionsStorage() {
        subscriptions = new ConcurrentHashMap<>();
        Optional<String> fileString = readCsvFile(SUBSCRIPTIONS_CSV_FILE);
        if (fileString.get().length() > 0) {
            String[] arrayRows = fileString.get().split(LF);
            for (String row : arrayRows) {
                String[] arrayWords = row.split(SEPARATOR_CSV);
                if (arrayWords.length > 0) {
                    subscriptions.put(UUID.fromString(arrayWords[0]), new Subscription(UUID.fromString(arrayWords[0]),
                            UsersStorage.getInstance().getUser(UUID.fromString(arrayWords[1])),
                            UsersStorage.getInstance().getUser(UUID.fromString(arrayWords[2])),
                            LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(arrayWords[3])), ZoneId.systemDefault())));
                }
            }
        }
    }

    private void rewrite() {
        StringBuilder contentSubscriptionsStorage = new StringBuilder();
        for (Map.Entry entry: subscriptions.entrySet()) {
            contentSubscriptionsStorage.append(((Subscription) entry.getValue()).getUuid().toString()).append(SEPARATOR_CSV)
                    .append(((Subscription) entry.getValue()).getFollower().getUuid().toString()).append(SEPARATOR_CSV)
                    .append(((Subscription) entry.getValue()).getPublisher().getUuid().toString()).append(SEPARATOR_CSV)
                    .append(((Subscription) entry.getValue()).getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()/1000).append(SEPARATOR_CSV)
                    .append(LF);
        }
        deleteContentCsvFile(SUBSCRIPTIONS_CSV_FILE);
        writeCsvFile(SUBSCRIPTIONS_CSV_FILE, contentSubscriptionsStorage.toString());
    }
}