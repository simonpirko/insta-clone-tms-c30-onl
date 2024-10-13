package by.tms.instaclone;

import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.SubscriptionsStorage;
import by.tms.instaclone.storage.UsersStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SubscriptionsStorageProbe {
    public static void main(String[] args) {
// работа со Storage

//        UsersStorage usersStorage = UsersStorage.getInstance();
//        User alex = usersStorage.getUser(UUID.fromString("0cdb37b3-75ab-4b65-bce6-09d3c4985578")); // Alex
//        User alex2 = usersStorage.getUser(UUID.fromString("d1085705-5a91-383d-b1b5-c49094657e10")); // Alex-2
//        User proton = usersStorage.getUser(UUID.fromString("91df09b5-69af-39b5-a64d-1506f50fa4bb")); // Proton
//        User atom = usersStorage.getUser(UUID.fromString("3e10f8c8-0924-3d3a-8f94-c18e7addb866")); // Atom
//        User nom1 = usersStorage.getUser(UUID.fromString("aa44061c-62f5-37a9-b126-43053134c2d6")); // Nom1
//        User nom = usersStorage.getUser(UUID.fromString("aee37c30-f5d0-31a4-9552-6f636a3527bb")); // Nom
//        User rome1 = usersStorage.getUser(UUID.fromString("029ad4c2-5c5c-309d-b578-604976d65aa1")); // Rome1
//        User publisherUser = UsersStorage.getInstance().getUser(UUID.fromString("aa44061c-62f5-37a9-b126-43053134c2d6"));
//        HashMap<UUID, User> followers = SubscriptionsStorage.getInstance().getFollowersPublisher(publisherUser);
//        User followerUser = UsersStorage.getInstance().getUser(UUID.fromString("d1085705-5a91-383d-b1b5-c49094657e10"));
//        HashMap<UUID, User> publishers = SubscriptionsStorage.getInstance().getPublishersFollower(followerUser);

//        SubscriptionsStorage subscriptionsStorage = SubscriptionsStorage.getInstance();
//        Subscription alexAlex3 = subscriptionsStorage.newSubscription(alex, alex3);
//        Subscription alexAtom = subscriptionsStorage.newSubscription(alex, atom);
//        Subscription alexNom1 = subscriptionsStorage.newSubscription(alex, nom1);
//        Subscription alex3Alex = subscriptionsStorage.newSubscription(alex3, alex);
//        Subscription nom1Newuser = subscriptionsStorage.newSubscription(nom1, newuser);
//        Subscription nom1Alex = subscriptionsStorage.newSubscription(nom1, alex);
//        Subscription atomNom1 = subscriptionsStorage.newSubscription(atom, nom1);
//        Subscription alex3Newuser = subscriptionsStorage.newSubscription(alex3, newuser);
//        Subscription alex3Nom1 = subscriptionsStorage.newSubscription(alex3, nom1);
//        Subscription deleteSubscription = subscriptionsStorage.getSubscription(UUID.fromString("29dbc229-a91f-3159-9840-958fa99791ed"));
//        subscriptionsStorage.deleteSubscription(deleteSubscription);

        List<User> followers = new ArrayList<>();
        followers = SubscriptionsStorage.getInstance().getFollowersPublisher(UUID.fromString("aa44061c-62f5-37a9-b126-43053134c2d6"));

int i = 0;
//        subscriptionsStorage.deleteSubscriptionPublisher(alex); // 3 to deleting    "0cdb37b3-75ab-4b65-bce6-09d3c4985578" - Alex
//        subscriptionsStorage.deleteSubscriptionFollower(alex); // 2 to deleting   "0cdb37b3-75ab-4b65-bce6-09d3c4985578" - Alex

//i = 1;
    }
}
