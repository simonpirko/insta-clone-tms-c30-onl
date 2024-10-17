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
    private String uuidPost;
    private long countLikeLastPost;
    private long countDislikeLastPost;
    private String carouselName;

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

    public void setUuidPost(String uuidPost) {
        this.uuidPost = uuidPost;
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

    public String getUuidPost() {
        return uuidPost;
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
}