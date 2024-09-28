package by.tms.instaclone;

import by.tms.instaclone.model.Subscription;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.SubscriptionsStorage;
import by.tms.instaclone.storage.UsersStorage;

import java.util.UUID;

public class SubscriptionsStorageProbe {
    public static void main(String[] args) {
// работа со Storage

        SubscriptionsStorage subscriptionsStorage = SubscriptionsStorage.getInstance();
//        UsersStorage usersStorage = UsersStorage.getInstance();
//        User subscriber = usersStorage.getUser(UUID.fromString("0cdb37b3-75ab-4b65-bce6-09d3c4985578")); // Alex
//        User publisher = usersStorage.getUser(UUID.fromString("d1085705-5a91-383d-b1b5-c49094657e10")); // Alex-3
//        Subscription newSubscription = subscriptionsStorage.newSubscription(subscriber, publisher);
        Subscription deleteSubscription = subscriptionsStorage.getSubscription(UUID.fromString("29dbc229-a91f-3159-9840-958fa99791ed"));
        subscriptionsStorage.deleteSubscription(deleteSubscription);


        int i = 0;
    }
}
