package by.tms.instaclone.DTOs;

public class ProfileDTO {
    private String username;
    private int statusSubscription;
    private int countSubscriber;
    private int countSubscription;
    private int countPost;
    //private Post[] postsProfile;
    //private byte[] avatar;

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

    /* public void setPostsProfile(Post[] postsProfile) {
        this.postsProfile = postsProfile;
    }*/

    /*public byte[] getAvatar() {
        return avatar;
    }*/
    /*public Post[] getPostsProfile() {
        return postsProfile;
    }*/
    /*public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }*/
    @Override
    public String toString() {
        return "ProfileDTO{" +
                "username='" + username + '\'' +
                ", countSubscriber=" + countSubscriber +
                ", countSubscription=" + countSubscription +
                ", countPost=" + countPost +
                '}';
    }
}

