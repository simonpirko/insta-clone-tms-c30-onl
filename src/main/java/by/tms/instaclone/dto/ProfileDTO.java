package by.tms.instaclone.dto;

import java.util.*;

public class ProfileDTO {
    private String username;
    private int statusSubscription;
    private int countSubscriber;
    private int countSubscription;
    private int countPost;
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private List<CardProfileDTO> cardProfileDTOS;

    public void setUsername(String username) {
        this.username = username;
    }

    public List<CardProfileDTO> getCardProfileDTOS() {
        return cardProfileDTOS;
    }

    public void setCardProfileDTOS(List<CardProfileDTO> cardProfileDTOS) {
        this.cardProfileDTOS = cardProfileDTOS;
    }

    public String getUsername() {
        return username;
    }

    public int getStatusSubscription() {
        return statusSubscription;
    }

    public void setStatusSubscription(int statusSubscription) {
        this.statusSubscription = statusSubscription;
    }

    public ProfileDTO(String username) {
        this.username = username;
    }

    public void setCountSubscriber(int countSubscriber) {
        this.countSubscriber = countSubscriber;
    }

    public void setCountSubscription(int countSubscription) {
        this.countSubscription = countSubscription;
    }

    public void setCountPost(int countPost) {
        this.countPost = countPost;
    }

    public String getCountSubscriber() {
        return Integer.toString(countSubscriber);
    }

    public String getCountSubscription() {
        return Integer.toString(countSubscription);
    }

    public String getCountPost() {
        return Integer.toString(countPost);
    }


    @Override
    public String toString() {
        return "ProfileDTO{" +
                "username='" + username + '\'' +
                ", statusSubscription=" + statusSubscription +
                ", countSubscriber=" + countSubscriber +
                ", countSubscription=" + countSubscription +
                ", countPost=" + countPost +

                '}';
    }
}

