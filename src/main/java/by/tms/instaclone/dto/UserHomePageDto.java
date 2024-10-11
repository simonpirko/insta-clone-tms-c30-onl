package by.tms.instaclone.dto;

import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.SubscriptionsStorage;

import java.util.HashMap;
import java.util.UUID;

public class UserHomePageDto {
    private static String name;
    private static String username;
//    private static List<User> publishers = new ArrayList<>();

    public UserHomePageDto(User user) {
        name = user.getName();
        username = user.getUsername();
        HashMap<UUID, User> publishers = SubscriptionsStorage.getInstance().getPublishersFollower(user);

        int i = 0;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

//    public List<PublisherCardDto> getPublishersCards() {
//        return publishersCards;
//    }
}
