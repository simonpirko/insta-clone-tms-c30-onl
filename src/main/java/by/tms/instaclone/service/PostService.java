package by.tms.instaclone.service;

import by.tms.instaclone.dto.CommentsDto;
import by.tms.instaclone.dto.PostDto;
import by.tms.instaclone.model.Comment;
import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.Reaction;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.CommentsStorage;
import by.tms.instaclone.storage.PhotoStorage;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.ReactionsStorage;
import by.tms.instaclone.utilites.CommentsComparator;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static by.tms.instaclone.storage.KeeperConstants.*;

public class PostService {

    PostsStorage postsStorage = PostsStorage.getInstance();
    CommentsStorage commentsStorage = CommentsStorage.getInstance();
    ReactionsStorage reactionsStorage = ReactionsStorage.getInstance();
    PhotoStorage photoStorage = PhotoStorage.getInstance();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_CREATE_POST_TEMPLATE);

    public PostDto getContent(UUID postUUID) {
        PostDto postDto = new PostDto();
        List<CommentsDto> commentsDtoList = new ArrayList<>();
        List<Reaction> reactionList = reactionsStorage.getAllReactionPost(postUUID);
        Post post = postsStorage.getPost(postUUID);

        int countLikes = 0;
        int countDislikes = 0;
        for (Reaction reaction : reactionList) {
            if (reaction.isTypeReaction()) {
                countLikes++;
            } else {
                countDislikes++;
            }
        }
        String username = post.getOwner().getUsername();
        postDto.setLikes(countLikes);
        postDto.setDislikes(countDislikes);
        postDto.setPostUUID(post.getUuid());
        postDto.setUsername(username);
        postDto.setUrlPublisher(USER_PROFILE_URL + SLAGE + username);
        postDto.setTextPost(post.getText());
        postDto.setCreatedAt(post.getCreateAt().format(dateTimeFormatter));

        List<Comment> comments = commentsStorage.getAllCommentsPost(postUUID)
                .stream()
                .sorted(new CommentsComparator())
                .toList();
        for(Comment comment : comments) {
            CommentsDto commentsDto = new CommentsDto();
            commentsDto.setCommentUUID(comment.getUuid());
            commentsDto.setUsername(comment.getOwner().getUsername());
            commentsDto.setTextComment(comment.getText());
            commentsDto.setCreatedAt(comment.getCreateAt().format(dateTimeFormatter));
            commentsDtoList.add(commentsDto);
        }
        postDto.setComments(commentsDtoList);
        postDto.setPhotos(photoStorage.getPhotosPost(postUUID));

        return postDto;
    }

    public Post setComment(UUID postUUID, String comment, User user) {
        Post post = postsStorage.getPost(postUUID);
        commentsStorage.newComment(post,user,comment);
        return post;
    }

    public void deleteComment(String commentUUID) {
        Comment comment = commentsStorage.getComment(UUID.fromString(commentUUID));
        commentsStorage.deleteComment(comment);
    }

    public void deletePost(UUID postUUID) {
        Post post = postsStorage.getPost(postUUID);
        postsStorage.deletePost(post);
    }

    public void reactionPost(UUID postUUID, User user, Boolean typeReaction) {
        Post post = postsStorage.getPost(postUUID);
        Optional<UUID> uuidReaction = reactionsStorage.findUuidReaction(postUUID, user.getUuid());
        String reaction = reactionsStorage.seeReaction(postUUID, user.getUuid());
        String  temp;
        if (typeReaction) {
            temp = "like";
        } else {
            temp = "dislike";
        }
        if (reaction.equals("none")) {
            reactionsStorage.newReaction(post,user,typeReaction);
        } else if (uuidReaction.isPresent()) {
            if (reaction.equals(temp)) {
                reactionsStorage.deleteReaction(reactionsStorage.getReaction(uuidReaction.get()));
            } else {
                reactionsStorage.changeReaction(reactionsStorage.getReaction(uuidReaction.get()), typeReaction);            }
        }
    }
}
