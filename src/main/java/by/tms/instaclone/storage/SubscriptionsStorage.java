package by.tms.instaclone.storage;

import by.tms.instaclone.model.Subscription;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

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

}
