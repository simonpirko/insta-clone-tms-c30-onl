package by.tms.instaclone.dto;

import java.util.List;

/**
 * Класс описывает содержимое профиля Публикатора
 */
public class PublisherCardLastPostDto {
    private String namePublisher;
    private String usernamePublisher;
    private String urlPublisher;
    private List<String> textLastPostPublisher;
    private List<String> createAtLastPost;
//    private static List<Image> photosPost;              // todo объект-фото не нужно, надо просто фото
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

    public void setTextLastPostPublisher(List<String> textLastPostPublisher) {
        this.textLastPostPublisher = textLastPostPublisher;
    }

    public void setCreateAtLastPost(List<String> createAtLastPost) {
        this.createAtLastPost = createAtLastPost;
    }

    public void setCarouselName(String carouselName) {
        this.carouselName = carouselName;
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

//    public List<Photo> getPhotosPost() {
//        return photosPost;
//    }

    public String getCarouselName() {
        return carouselName;
    }

    public PublisherCardLastPostDto getPublisherCard() {
        return this;
    }
}