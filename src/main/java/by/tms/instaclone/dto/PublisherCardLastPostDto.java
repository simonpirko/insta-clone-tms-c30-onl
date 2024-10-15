package by.tms.instaclone.dto;

import java.util.List;

/**
 * Класс описывает  контент для "Карточка Последнего поста Публикатора"
 */
public class PublisherCardLastPostDto {
    private String namePublisher;
    private String usernamePublisher;
    private String urlPublisher;
    private List<String> textLastPostPublisher;
    private List<String> createAtLastPost;
    private List<String> photosLastPost;
    private long countLikeLastPost;
    private long countDislikeLastPost;
    private String carouselName;
    private String likeBottom;
    private String dislikeBottom;
    private String CommentBottom;

    public void setNamePublisher(String namePublisher) {
        this.namePublisher = namePublisher;
    }

    public void setUsernamePublisher(String usernamePublisher) {
        this.usernamePublisher = usernamePublisher;
    }

    public void setUrlPublisher(String urlPublisher) {
        this.urlPublisher = urlPublisher;
    }

    public void setTextLastPost(List<String> textLastPostPublisher) {
        this.textLastPostPublisher = textLastPostPublisher;
    }

    public void setCreateAtLastPost(List<String> createAtLastPost) {
        this.createAtLastPost = createAtLastPost;
    }

    public void setPhotosLastPost(List<String> photosLastPost) {
        this.photosLastPost = photosLastPost;
    }

    public void setCarouselName(String carouselName) {
        this.carouselName = carouselName;
    }

    public void setCountLikeLastPost(long countLikeLastPost) {
        this.countLikeLastPost = countLikeLastPost;
    }

    public void setCountDislikeLastPost(long countDislikeLastPost) {
        this.countDislikeLastPost = countDislikeLastPost;
    }

    public void setLikeBottom(String likeBottom) {
        this.likeBottom = likeBottom;
    }

    public void setDislikeBottom(String dislikeBottom) {
        this.dislikeBottom = dislikeBottom;
    }

    public void setCommentBottom(String commentBottom) {
        CommentBottom = commentBottom;
    }

    public String getNamePublisher() {
        return namePublisher;
    }

    public String getUsernamePublisher() {
        return usernamePublisher;
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

    public List<String> getPhotosLastPost() {
        return photosLastPost;
    }

    public String getCarouselName() {
        return carouselName;
    }

    public long getCountLikeLastPost() {
        return countLikeLastPost;
    }

    public long getCountDislikeLastPost() {
        return countDislikeLastPost;
    }

    public String getLikeBottom() {
        return likeBottom;
    }

    public String getDislikeBottom() {
        return dislikeBottom;
    }

    public String getCommentBottom() {
        return CommentBottom;
    }
}