package by.tms.instaclone.service;

import by.tms.instaclone.dto.PublisherCardLastPostDto;
import by.tms.instaclone.model.Post;
import by.tms.instaclone.storage.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static by.tms.instaclone.storage.KeeperConstants.*;

/**
 * Класс описывает контент Публикатора
 */
public class PublisherService {

    /**
     * Класс формирует контент для "Карточка Последнего поста Публикатора"
     */
    public Optional<PublisherCardLastPostDto> collectLastPost(String usernamePublisher) {
        PublisherCardLastPostDto publisherCardLastPostDto = new PublisherCardLastPostDto();
        UUID uuidPublisher = UsernamesStorage.getInstance().getUUID(usernamePublisher);
        publisherCardLastPostDto.setNamePublisher(UsersStorage.getInstance().getUser(uuidPublisher).getName());
        publisherCardLastPostDto.setUsernamePublisher(usernamePublisher);
        publisherCardLastPostDto.setUrlPublisher(USER_PROFILE_URL + SLAGE + usernamePublisher);
        List<Post> lastPostsPublisher = new ArrayList<>(PostsStorage.getInstance().getLastPostOwner(uuidPublisher));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_CREATE_POST_TEMPLATE);
        List<String> textLastPost = new ArrayList<>();
        List<String> createAtLastPost = new ArrayList<>();
        List<String> photosLastPost = new ArrayList<>();
        long countLikeLastPost = 0;
        long countDislikeLastPost = 0;
        int count = 0;
        for (Post post : lastPostsPublisher) {
            textLastPost.add(post.getText());
            createAtLastPost.add(post.getCreateAt().format(dateTimeFormatter));
            photosLastPost = PhotoStorage.getInstance().getPhotosPost(post.getUuid());
            countLikeLastPost = ReactionsStorage.getInstance().getCountLikePost(post.getUuid());
            countDislikeLastPost = ReactionsStorage.getInstance().getCountDislikePost(post.getUuid());
            publisherCardLastPostDto.setUuidPost(post.getUuid().toString());
        }
        publisherCardLastPostDto.setTextLastPost(textLastPost);
        publisherCardLastPostDto.setCreateAtLastPost(createAtLastPost);
        publisherCardLastPostDto.setPhotosLastPost(photosLastPost);
        publisherCardLastPostDto.setCountLikeLastPost(countLikeLastPost);
        publisherCardLastPostDto.setCountDislikeLastPost(countDislikeLastPost);
        return Optional.of(publisherCardLastPostDto);
    }
}