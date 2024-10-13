package by.tms.instaclone.dto;

import by.tms.instaclone.model.Post;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.UsersStorage;
import by.tms.instaclone.storage.UsernamesStorage;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static by.tms.instaclone.storage.KeeperConstants.*;

public class PublisherCardDto {
    private final String namePublisher;
    private final String userNamePublisher;
    private final String urlPublisher;
    private final List<String> textLastPostPublisher;
    private final List<String> createAtLastPost;
//    private static List<Image> photosPost;              // todo объект-фото не нужно, надо просто фото
    private final String carouselName;

    public PublisherCardDto(String userName) {
        UUID uuidPublisher = UsernamesStorage.getInstance().getUUID(userName);
        userNamePublisher = UsersStorage.getInstance().getUser(uuidPublisher).getUsername();
        namePublisher = UsersStorage.getInstance().getUser(uuidPublisher).getName();
        urlPublisher = USER_PROFILE_URL + SLAGE + userNamePublisher;
        List<Post> lastPostsPublisher = new ArrayList<>(PostsStorage.getInstance().getLastPostOwner(uuidPublisher));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_CREATE_POST_TEMPLATE);
        textLastPostPublisher = new ArrayList<>();
        createAtLastPost = new ArrayList<>();
        for (Post post : lastPostsPublisher) {
            textLastPostPublisher.add(post.getText());
            createAtLastPost.add(post.getCreateAt().format(dateTimeFormatter));
        }
        carouselName = "Carousel^" + uuidPublisher;

        int i = 0; // todo delete after debugging!
    }

    public String getNamePublisher() {
        return namePublisher;
    }

    public String getUserNamePublisher() {
        return userNamePublisher;
    }

    public String getUrlPublisher() {
        return urlPublisher;
    }

    public List<String> getTextLastPostPublisher() {
        return textLastPostPublisher;
    }

    public List<String> getCreateAtLastPost() {
        return createAtLastPost;
    }

//    public List<Photo> getPhotosPost() {
//        return photosPost;
//    }

    public String getCarouselName() {
        return carouselName;
    }

    public PublisherCardDto getPublisherCard() {
        return this;
    }
}