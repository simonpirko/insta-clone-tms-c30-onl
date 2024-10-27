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

public class RecommendationsService {

    SubscriptionsStorage subscriptionsStorage = SubscriptionsStorage.getInstance();

    public List<UserDTO> searchUsers(User currentUser) {
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
        for (int i = 0; i < users.size(); i++) {
            count++;
            result.add(users.get(i));
            if (count == 10) {
                break;
            }
        }
        return result;
    }

}
