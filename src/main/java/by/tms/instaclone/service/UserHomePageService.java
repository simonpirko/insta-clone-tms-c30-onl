package by.tms.instaclone.service;

import by.tms.instaclone.dto.PublisherCardLastPostDto;
import by.tms.instaclone.dto.UserHomePageDto;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.SubscriptionsStorage;
import by.tms.instaclone.storage.UsernamesStorage;
import by.tms.instaclone.storage.UsersStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Класс описывает формирование контента для страницы ./user/home
 */
public class UserHomePageService {
    private final String usernameOwner;

    public UserHomePageService(String username) {
        this.usernameOwner = username;
    }

    public Optional<UserHomePageDto> collect() {
        UserHomePageDto userHomePageDto = new UserHomePageDto();
        UUID uuidOwner = UsernamesStorage.getInstance().getUUID(usernameOwner);
        User owner = UsersStorage.getInstance().getUser(uuidOwner);
        userHomePageDto.setName(owner.getName());
        userHomePageDto.setUsername(owner.getUsername());
        List<User> publishers = SubscriptionsStorage.getInstance().getPublishersFollower(owner.getUuid());
        List<PublisherCardLastPostDto> publishersCards = new ArrayList<>();
        for (User publisher : publishers) {
            PublisherCardLastPostService content = new PublisherCardLastPostService(publisher.getUsername());
            Optional<PublisherCardLastPostDto> publisherCard = content.collect();
            if (!publisherCard.isEmpty()) {
                publishersCards.add(publisherCard.get());
            }
        }
        userHomePageDto.setPublishersCards(publishersCards);
        return Optional.of(userHomePageDto);
    }

}
