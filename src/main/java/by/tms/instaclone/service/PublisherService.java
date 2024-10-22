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
 * Класс описывает формирование контента Публикатора
 */
public class PublisherService {
    final UsersStorage usersStorage = UsersStorage.getInstance();
    final ReactionsStorage reactionsStorage = ReactionsStorage.getInstance();
    final PhotoStorage photoStorage = PhotoStorage.getInstance();
    final PostsStorage postsStorage = PostsStorage.getInstance();

    /**
     * Метод формирует контент для "Карточка Последнего поста Публикатора"
     */
    public Optional<PublisherCardLastPostDto> collectLastPost(UUID followerUuid, UUID publisherUuid) {
        PublisherCardLastPostDto publisherCardLastPostDto = new PublisherCardLastPostDto();
        String namePublisher = usersStorage.getUser(publisherUuid).getName();
        publisherCardLastPostDto.setNamePublisher(namePublisher);
        String usernamePublisher = usersStorage.getUser(publisherUuid).getUsername();
        publisherCardLastPostDto.setUsernamePublisher(usernamePublisher);
        publisherCardLastPostDto.setUrlPublisher(USER_PROFILE_URL + SLAGE + usernamePublisher);
        List<Post> lastPostsPublisher = new ArrayList<>(postsStorage.getLastPostOwner(publisherUuid));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_CREATE_POST_TEMPLATE);
        List<String> textLastPost = new ArrayList<>();
        List<String> createAtLastPost = new ArrayList<>();
        List<String> photosLastPost = new ArrayList<>();
        long countLikeLastPost = 0;
        long countDislikeLastPost = 0;
        for (Post post : lastPostsPublisher) {
            textLastPost.add(post.getText());
            createAtLastPost.add(post.getCreateAt().format(dateTimeFormatter));
            photosLastPost = photoStorage.getPhotosPost(post.getUuid());
            countLikeLastPost = reactionsStorage.getCountLikePost(post.getUuid());
            countDislikeLastPost = reactionsStorage.getCountDislikePost(post.getUuid());
            publisherCardLastPostDto.setUuidPost(post.getUuid().toString());
            publisherCardLastPostDto.setValueReaction(reactionsStorage.seeReaction(post.getUuid(), followerUuid));
        }
        publisherCardLastPostDto.setTextLastPost(textLastPost);
        publisherCardLastPostDto.setCreateAtLastPost(createAtLastPost);
        publisherCardLastPostDto.setPhotosLastPost(photosLastPost);
        publisherCardLastPostDto.setCountLikeLastPost(countLikeLastPost);
        publisherCardLastPostDto.setCountDislikeLastPost(countDislikeLastPost);
        return Optional.of(publisherCardLastPostDto);
    }
}