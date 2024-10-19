package by.tms.instaclone.service;

import by.tms.instaclone.dto.CommentsDto;
import by.tms.instaclone.dto.PostDto;
import by.tms.instaclone.model.Comment;
import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.CommentsStorage;
import by.tms.instaclone.storage.PhotoStorage;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.ReactionsStorage;
import by.tms.instaclone.utilites.CommentsComparator;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static by.tms.instaclone.storage.KeeperConstants.DATE_TIME_CREATE_POST_TEMPLATE;

public class PostService {

    PostsStorage postsStorage = PostsStorage.getInstance();
    CommentsStorage commentsStorage = CommentsStorage.getInstance();
    ReactionsStorage reactionsStorage = ReactionsStorage.getInstance();
    PhotoStorage photoStorage = PhotoStorage.getInstance();
    PostDto postDto = new PostDto();
    List<CommentsDto> commentsDtoList = new ArrayList<>();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_CREATE_POST_TEMPLATE);

    public PostDto getContent(UUID postUUID) {
        Post post = postsStorage.getPost(postUUID);
        postDto.setPostUUID(post.getUuid());
        postDto.setUsername(post.getOwner().getUsername());
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
}
