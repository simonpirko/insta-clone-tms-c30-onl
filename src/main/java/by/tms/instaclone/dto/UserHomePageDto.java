package by.tms.instaclone.dto;

import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.SubscriptionsStorage;
import by.tms.instaclone.storage.UsernamesStorage;
import by.tms.instaclone.storage.UsersStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserHomePageDto {
    private final String name;
    private final String username;
    private final List<PublisherCardDto> publishersCards;

    public UserHomePageDto(String username1) {
        UUID uuid = UsernamesStorage.getInstance().getUUID(username1);
        User user = UsersStorage.getInstance().getUser(uuid);
        name = user.getName();
        username = user.getUsername();
        List<User> publishers = SubscriptionsStorage.getInstance().getPublishersFollower(user.getUuid());
        publishersCards = new ArrayList<>();
        for (User publisher : publishers) {
            PublisherCardDto pcDto = new PublisherCardDto(publisher.getUsername());
            publishersCards.add(pcDto);
        }
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