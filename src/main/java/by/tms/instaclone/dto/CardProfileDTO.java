package by.tms.instaclone.dto;

import java.util.List;
import java.util.UUID;

public class CardProfileDTO {
    private String postUUID;
    private String textPostProfile;
    private String createAtPost;
    private List<String> photosPost;
    private String carouselName;

    public String getPostUUID() {
        return postUUID;
    }

    public void setPostUUID(String postUUID) {
        this.postUUID = postUUID;
    }

    public String getTextPostProfile() {
        return textPostProfile;
    }

    public String getCarouselName() {
        return carouselName;
    }

    public void setCarouselName(String carouselName) {
        this.carouselName = carouselName;
    }

    public void setTextPostProfile(String textPostProfile) {
        this.textPostProfile = textPostProfile;
    }

    public String getCreateAtPost() {
        return createAtPost;
    }

    public void setCreateAtPost(String createAtPost) {
        this.createAtPost = createAtPost;
    }

    public List<String> getPhotosPost() {
        return photosPost;
    }

    public void setPhotosPost(List<String> photosPost) {
        this.photosPost = photosPost;
    }
}
