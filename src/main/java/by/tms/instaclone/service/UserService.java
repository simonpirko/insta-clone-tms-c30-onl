package by.tms.instaclone.service;

import by.tms.instaclone.dto.PublisherCardLastPostDto;
import by.tms.instaclone.dto.UserDTO;
import by.tms.instaclone.dto.UserHomePageDto;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.PhotoStorage;
import by.tms.instaclone.storage.SubscriptionsStorage;
import by.tms.instaclone.storage.UsernamesStorage;
import by.tms.instaclone.storage.UsersStorage;

import java.util.*;

import static by.tms.instaclone.storage.KeeperConstants.*;

/**
 * Класс описывает формирование контента для Пользователя
 */
public class UserService {
    final UsersStorage usersStorage = UsersStorage.getInstance();
    final SubscriptionsStorage subscriptionsStorage = SubscriptionsStorage.getInstance();
    final UsernamesStorage usernamesStorage = UsernamesStorage.getInstance();
    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }


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
            if (!publisherCard.isEmpty()) {
                publishersCards.add(publisherCard.get());
            }
        }
        userHomePageDto.setPublishersCards(publishersCards);
        return Optional.of(userHomePageDto);
    }

    /**
     * Метод формирует и возвращает UserDTO по его username
     */
    public Optional<UserDTO> getUserByUsername(String username) {
        try {
            Optional<User> u = Optional.ofNullable(UsersStorage.getInstance().getUser(username));
            UserDTO UserDTO = new UserDTO();
            if (u.isPresent()) {
                User user = u.get();
                UserDTO.setUsername(username);
                UserDTO.setName(user.getName());
                UserDTO.setUrlUser(USER_PROFILE_URL + SLAGE + username);
                Optional<byte[]> image = PhotoStorage.getInstance().
                        getByteAvatar(UsernamesStorage.getInstance().
                                getUUID(username).toString());
                byte[] avatarBytes = image.orElse(PhotoStorage.getInstance().getByteAvatar("DefaultAvatar").get());
                String avatar = Base64.getEncoder().encodeToString(avatarBytes);
                UserDTO.setAvatar(avatar);
                return Optional.of(UserDTO);
            }
        } catch (Exception e) {
            return Optional.empty();
        }

        return Optional.empty();
    }
}
