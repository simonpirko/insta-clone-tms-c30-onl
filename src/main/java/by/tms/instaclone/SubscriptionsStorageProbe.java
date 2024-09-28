package by.tms.instaclone;

import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.SubscriptionsStorage;
import by.tms.instaclone.storage.UsersStorage;

import java.util.UUID;

public class SubscriptionsStorageProbe {
    public static void main(String[] args) {
// работа со Storage
        UsersStorage usersStorage = UsersStorage.getInstance();
        User alex = usersStorage.getUser(UUID.fromString("0cdb37b3-75ab-4b65-bce6-09d3c4985578")); // Alex
        User alex3 = usersStorage.getUser(UUID.fromString("d1085705-5a91-383d-b1b5-c49094657e10")); // Alex-3
        User nom1 = usersStorage.getUser(UUID.fromString("aa44061c-62f5-37a9-b126-43053134c2d6")); // Nom1
        User newuser = usersStorage.getUser(UUID.fromString("18468996-fc49-3675-8fa4-37399481ced5")); // NewUser
        User atom = usersStorage.getUser(UUID.fromString("3e10f8c8-0924-3d3a-8f94-c18e7addb866")); // Atom

        SubscriptionsStorage subscriptionsStorage = SubscriptionsStorage.getInstance();
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
int i = 0;
        subscriptionsStorage.deleteSubscriptionPublisher(alex); // 3 to deleting    "0cdb37b3-75ab-4b65-bce6-09d3c4985578" - Alex
        subscriptionsStorage.deleteSubscriptionSubscriber(alex); // 2 to deleting   "0cdb37b3-75ab-4b65-bce6-09d3c4985578" - Alex

i = 1;
    }
}
