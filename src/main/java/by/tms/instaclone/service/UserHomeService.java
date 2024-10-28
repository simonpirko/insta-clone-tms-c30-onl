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
 * Класс описывает формирование контента для Пользователя
 */
public class UserHomeService {
    final UsersStorage usersStorage = UsersStorage.getInstance();
    final SubscriptionsStorage subscriptionsStorage = SubscriptionsStorage.getInstance();
    final UsernamesStorage usernamesStorage = UsernamesStorage.getInstance();

    /**
     * Метод формирует контент для HOME_USER_JSP
     */
    public Optional<UserHomePageDto> collectHomePageContent(String usernameOwner) {
        UserHomePageDto userHomePageDto = new UserHomePageDto();
        UUID uuidOwner = usernamesStorage.getUUID(usernameOwner);
        User owner = usersStorage.getUser(uuidOwner);
        userHomePageDto.setName(owner.getName());
        userHomePageDto.setUsername(owner.getUsername());
        List<User> publishers = subscriptionsStorage.getPublishersFollower(owner.getUuid());
        List<PublisherCardLastPostDto> publishersCards = new ArrayList<>();
        for (User publisher : publishers) {
            PublisherService content = new PublisherService();
            Optional<PublisherCardLastPostDto> publisherCard = content.collectLastPost(owner.getUuid(), publisher.getUuid());
            if ((!publisherCard.isEmpty()) && (!publisherCard.get().getPhotosLastPost().isEmpty())) {
                publishersCards.add(publisherCard.get());
            }
        }
        userHomePageDto.setPublishersCards(publishersCards);
        return Optional.of(userHomePageDto);
    }
}