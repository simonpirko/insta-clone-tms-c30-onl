package by.tms.instaclone.dto;

import by.tms.instaclone.model.Post;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.UsersStorage;
import by.tms.instaclone.storage.UsernamesStorage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static by.tms.instaclone.storage.KeeperConstants.SLAGE;
import static by.tms.instaclone.storage.KeeperConstants.USER_PROFILE_URL;

public class PublisherCardDto {
    private final String namePublisher;
    private final String userNamePublisher;
    private final String urlPublisher;
    private final List<String> textLastPostPublisher;
    private final List<LocalDateTime> createAtLastPost;
//    private static List<Image> photosPost;              // todo объект-фото не нужно, надо просто фото
    private final String carouselName;
    private final String carouselBottomPrevious;
    private final String carouselBottomNext;
    private final String likeBottom;
    private final String dislikeBottom;
    private final String commentBottom;

    public PublisherCardDto(String userName) {
        UUID uuidPublisher = UsernamesStorage.getInstance().getUUID(userName);
        userNamePublisher = UsersStorage.getInstance().getUser(uuidPublisher).getUsername();
        namePublisher = UsersStorage.getInstance().getUser(uuidPublisher).getName();
        urlPublisher = USER_PROFILE_URL + SLAGE + userNamePublisher;
        List<Post> lastPostsPublisher = new ArrayList<>(PostsStorage.getInstance().getLastPostOwner(uuidPublisher));
        textLastPostPublisher = new ArrayList<>();
        createAtLastPost = new ArrayList<>();
        for (Post post : lastPostsPublisher) {
            textLastPostPublisher.add(post.getText());
            createAtLastPost.add(post.getCreateAt());
        }
        carouselName = "Carousel^" + uuidPublisher;
        carouselBottomPrevious = "CarouselBottomPrevious^" + uuidPublisher;
        carouselBottomNext = "CarouselBottomNext^" + uuidPublisher;
        likeBottom = "Like^" + uuidPublisher;
        dislikeBottom = "Dislike^" + uuidPublisher;
        commentBottom = "Comment^" + uuidPublisher;

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
        return new ArrayList<>(textLastPostPublisher);
    }

    public List<LocalDateTime> getCreatePost() {
        return new ArrayList<>(createAtLastPost);
    }

//    public List<Photo> getPhotosPost() {
//        return photosPost;
//    }

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