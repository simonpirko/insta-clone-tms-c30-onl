package by.tms.instaclone;

import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.SubscriptionsStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SubscriptionsStorageProbe {
    public static void main(String[] args) {
// работа со Storage
        List<User> followers = new ArrayList<>(); // будут: Atom, Alex-2, Rome1
        followers = SubscriptionsStorage.getInstance().getFollowersPublisher(UUID.fromString("aa44061c-62f5-37a9-b126-43053134c2d6"));

        boolean isSubscribed = SubscriptionsStorage.getInstance().isSubscription(UUID.fromString("029ad4c2-5c5c-309d-b578-604976d65aa1"),
                UUID.fromString("91df09b5-69af-39b5-a64d-1506f50fa4bb")); // false
        boolean isSubscribed2 = SubscriptionsStorage.getInstance().isSubscription(UUID.fromString("029ad4c2-5c5c-309d-b578-604976d65aa1"),
                UUID.fromString("aa44061c-62f5-37a9-b126-43053134c2d6")); // true

int i = 0;
//i = 1;
    }
}
