package by.tms.instaclone.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class CommentsDto {

    private UUID comentUUID;
    private String username;
    private String textComment;
    private String createdAt;

    public UUID getComentUUID() {
        return comentUUID;
    }

    public void setComentUUID(UUID comentUUID) {
        this.comentUUID = comentUUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTextComment() {
        return textComment;
    }

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
