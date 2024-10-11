package by.tms.instaclone.dto;

import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.SubscriptionsStorage;
import by.tms.instaclone.storage.UsernamesStorage;
import by.tms.instaclone.storage.UsersStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserHomePageDto {
    private static String name;
    private static String username;
    private static List<PublisherCardDto> publishersCards = new ArrayList<>();

    public UserHomePageDto(String username1) {
        UUID uuid = UsernamesStorage.getInstance().getUUID(username1);
        User user = UsersStorage.getInstance().getUser(uuid);
        name = user.getName();
        username = user.getUsername();
        List<User> publishers = SubscriptionsStorage.getInstance().getPublishersFollower(user.getUuid());
        for (User publisher : publishers) {
            publishersCards.add(new PublisherCardDto(publisher.getUsername()));
        }

        int i = 0;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public List<PublisherCardDto> getPublishersCards() {
        return publishersCards;
    }
}