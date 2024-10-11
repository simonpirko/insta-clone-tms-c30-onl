package by.tms.instaclone.dto;

import by.tms.instaclone.model.Post;
import by.tms.instaclone.storage.PhotoStorage;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.UsersStorage;
import by.tms.instaclone.storage.UsernamesStorage;
import by.tms.instaclone.model.Photo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static by.tms.instaclone.storage.KeeperConstants.SLAGE;
import static by.tms.instaclone.storage.KeeperConstants.USER_PROFILE_URL;

public class PublisherCardDto {
    private static String namePublisher;
    private static String userNamePublisher;
    private static String urlPublisher;
    private static List<Post> postsPublisher;
    private static LocalDateTime createAt;
    private static List<Photo> photosPost;
    private static String carouselName;
    private static String carouselBottomPrevious;
    private static String carouselBottomNext;
    private static String likeBottom;
    private static String dislikeBottom;
    private static String commentBottom;

    public PublisherCardDto(String userName) {
        UUID uuidPublisher = UsernamesStorage.getInstance().getUUID(userName);
        userNamePublisher = UsersStorage.getInstance().getUser(uuidPublisher).getUsername();
        namePublisher = UsersStorage.getInstance().getUser(uuidPublisher).getName();
        urlPublisher = USER_PROFILE_URL + SLAGE + userNamePublisher;
        List<Post> postsPublisher = PostsStorage.getInstance().getLastPostOwner(uuidPublisher);
        createAt = PostsStorage.getInstance().getPost(uuidPublisher).getCreateAt();
        photosPost = new ArrayList<>(PhotoStorage.getInstance().getPhotosPost(uuidPublisher));
        carouselName = uuidPublisher + "Carousel";
        carouselBottomPrevious = uuidPublisher + "CarouselBottomPrevious";
        carouselBottomNext = uuidPublisher + "CarouselBottomNext";
        likeBottom = uuidPublisher + "Like";
        dislikeBottom = uuidPublisher + "Dislike";
        commentBottom = uuidPublisher + "Comment";
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

    public List<Post> getPostsPublisher() {
        return postsPublisher;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public List<Photo> getPhotosPost() {
        return photosPost;
    }

    public String getCarouselName() {
        return carouselName;
    }

    public String getCarouselBottomPrevious() {
        return carouselBottomPrevious;
    }

    public String getCarouselBottomNext() {
        return carouselBottomNext;
    }

    public String getLikeBottom() {
        return likeBottom;
    }

    public String getDislikeBottom() {
        return dislikeBottom;
    }

    public String getCommentBottom() {
        return commentBottom;
    }

    public PublisherCardDto getPublisherCard() {
        return this;
    }
}