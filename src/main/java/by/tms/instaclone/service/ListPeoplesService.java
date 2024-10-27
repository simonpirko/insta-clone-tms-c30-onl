package by.tms.instaclone.service;

import by.tms.instaclone.dto.UserDTO;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.SubscriptionsStorage;
import by.tms.instaclone.storage.UsersStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ListPeoplesService {

    SubscriptionsStorage subscriptionsStorage = SubscriptionsStorage.getInstance();

    public List<UserDTO> searchRecommendations(User currentUser) {
        List<UserDTO> users = new ArrayList<>();
        UserService userService = new UserService();
        ConcurrentHashMap<UUID, User> userMap = UsersStorage.getInstance().getUsers();
        for (UUID uuid : userMap.keySet()) {
            User user = userMap.get(uuid);
            if ((!subscriptionsStorage.isSubscription(currentUser.getUuid(), uuid)) && (!currentUser.getUuid().equals(uuid))) {
                users.add(userService.getUserByUsername(user.getUsername()).get());
            }
        }
        Collections.shuffle(users);
        List<UserDTO> result = new ArrayList<>();
        int count = 0;
        for (UserDTO user : users) {
            count++;
            result.add(user);
            if (count == 10) {
                break;
            }
        }
        return result;
    }

    public List<UserDTO> searchSubscription(User currentUser) {
        List<UserDTO> users = new ArrayList<>();
        UserService userService = new UserService();
        List<User> subscriptions = subscriptionsStorage.getFollowersPublisher(currentUser.getUuid());
        for (User user : subscriptions) {
            users.add(userService.getUserByUsername(user.getUsername()).get());
        }
        return users;
    }

    public List<UserDTO> searchSubscriber(User currentUser) {
        List<UserDTO> users = new ArrayList<>();
        UserService userService = new UserService();
        List<User> subscriptions = subscriptionsStorage.getPublishersFollower(currentUser.getUuid());
        for (User user : subscriptions) {
            users.add(userService.getUserByUsername(user.getUsername()).get());
        }
        return users;
    }


}
