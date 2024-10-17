package by.tms.instaclone.dto;

import by.tms.instaclone.model.Reaction;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {

   private String username;
   private String textPost;
   private LocalDateTime createdAt;
   private List<Reaction> reactions;
   private List<CommentsDto> comments;

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

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
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
}
