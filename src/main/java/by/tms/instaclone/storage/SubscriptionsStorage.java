package by.tms.instaclone.storage;

import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.Subscription;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static by.tms.instaclone.storage.Deleter.deleteContentCsvFile;
import static by.tms.instaclone.storage.KeeperConstants.*;
import static by.tms.instaclone.storage.Reader.readCsvFile;
import static by.tms.instaclone.storage.Writer.writeCsvFile;

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

    public void newSubscription(Subscription subscription) {
        subscriptions.put(subscription.getUuid(), subscription);
        // todo: с переходом к БД - сделать как с Объектом
        String rowText = SUBSCRIPTIONS_CSV_FORMAT_TEMPLATE.formatted(subscription.getUuid().toString(),
                subscription.getSubscriber().getUuid().toString(), subscription.getPublisher().getUuid().toString(),
                subscription.getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()/1000);
        writeCsvFile(SUBSCRIPTIONS_CSV_FILE, rowText);
    }

    private SubscriptionsStorage() {
        subscriptions = new ConcurrentHashMap<>();
        Optional<String> fileString = readCsvFile(SUBSCRIPTIONS_CSV_FILE);
        if (fileString.get().length() > 0) {
            String[] arrayRows = fileString.get().split(LF);
            for (String row : arrayRows) {
                String[] arrayWords = row.split(SEPARATOR_CSV);
                if ((arrayWords.length % 4) == 0) {
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
                    .append(((Subscription) entry.getValue()).getSubscriber().getUuid().toString()).append(SEPARATOR_CSV)
                    .append(((Subscription) entry.getValue()).getPublisher().getUuid().toString()).append(SEPARATOR_CSV)
                    .append(((Subscription) entry.getValue()).getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()/1000).append(SEPARATOR_CSV)
                    .append(LF);
        }
        deleteContentCsvFile(SUBSCRIPTIONS_CSV_FILE);
        writeCsvFile(SUBSCRIPTIONS_CSV_FILE, contentSubscriptionsStorage.toString());
    }

    private void deleteHeirs(Subscription subscription) {
        // todo
    }

    private void substitute(Subscription oldSubscription, Subscription newSubscription) {
//        deleteSubscription(oldSubscription);
//        newSubscription(newSubscription);
    }
}
