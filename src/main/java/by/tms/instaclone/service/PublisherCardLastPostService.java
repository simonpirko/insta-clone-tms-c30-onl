package by.tms.instaclone.service;

import by.tms.instaclone.dto.PublisherCardLastPostDto;
import by.tms.instaclone.model.Post;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.UsernamesStorage;
import by.tms.instaclone.storage.UsersStorage;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static by.tms.instaclone.storage.KeeperConstants.*;

/**
 * Класс формирует контент для "Карточка Последнего поста Публикатора"
 */
public class PublisherCardLastPostService {
    private final String usernamePublisher;

    public PublisherCardLastPostService(String username) {
        usernamePublisher = username;
    }

    public Optional<PublisherCardLastPostDto> collect() {
        PublisherCardLastPostDto publisherCardLastPostDto = new PublisherCardLastPostDto();
        UUID uuidPublisher = UsernamesStorage.getInstance().getUUID(usernamePublisher);
        publisherCardLastPostDto.setNamePublisher(UsersStorage.getInstance().getUser(uuidPublisher).getName());
        publisherCardLastPostDto.setUsernamePublisher(usernamePublisher);
        publisherCardLastPostDto.setUrlPublisher(USER_PROFILE_URL + SLAGE + usernamePublisher);
        List<Post> lastPostsPublisher = new ArrayList<>(PostsStorage.getInstance().getLastPostOwner(uuidPublisher));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_CREATE_POST_TEMPLATE);
        List<String> textLastPostPublisher = new ArrayList<>();
        List<String> createAtLastPost = new ArrayList<>();
        for (Post post : lastPostsPublisher) {
            textLastPostPublisher.add(post.getText());
            createAtLastPost.add(post.getCreateAt().format(dateTimeFormatter));
        }
        publisherCardLastPostDto.setTextLastPostPublisher(textLastPostPublisher);
        publisherCardLastPostDto.setCreateAtLastPost(createAtLastPost);
        publisherCardLastPostDto.setCarouselName("Carousel" + "-" + usernamePublisher);
        return Optional.of(publisherCardLastPostDto);
    }
}
