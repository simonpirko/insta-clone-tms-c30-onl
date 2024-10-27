package by.tms.instaclone.dto;

import by.tms.instaclone.model.Photo;
import by.tms.instaclone.model.Reaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PostDto {

   private UUID postUUID;
   private String username;
   private String textPost;
   private String createdAt;
   private String urlPublisher;
   private List<Reaction> reactions;
   private List<CommentsDto> comments;
   private List<String> photos;
   private int likes;
   private int dislikes;

   public String getUrlPublisher() {
      return urlPublisher;
   }

   public void setUrlPublisher(String urlPublisher) {
      this.urlPublisher = urlPublisher;
   }

   public int getLikes() {
      return likes;
   }

   public void setLikes(int likes) {
      this.likes = likes;
   }

   public int getDislikes() {
      return dislikes;
   }

   public void setDislikes(int dislikes) {
      this.dislikes = dislikes;
   }

   public UUID getPostUUID() {
      return postUUID;
   }

   public void setPostUUID(UUID postUUID) {
      this.postUUID = postUUID;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getTextPost() {
      return textPost;
   }

   public void setTextPost(String textPost) {
      this.textPost = textPost;
   }

   public String getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(String createdAt) {
      this.createdAt = createdAt;
   }

   public List<Reaction> getReactions() {
      return reactions;
   }

   public void setReactions(List<Reaction> reactions) {
      this.reactions = reactions;
   }

   public List<CommentsDto> getComments() {
      return comments;
   }

   public void setComments(List<CommentsDto> comments) {
      this.comments = comments;
   }

   public List<String> getPhotos() {
      return photos;
   }

   public void setPhotos(List<String> photos) {
      this.photos = photos;
   }
}
