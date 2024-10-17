package by.tms.instaclone.service;

import by.tms.instaclone.dto.PostDto;
import by.tms.instaclone.storage.CommentsStorage;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.ReactionsStorage;

import java.util.UUID;

public class PostService {

    PostsStorage postsStorage = PostsStorage.getInstance();
    CommentsStorage commentsStorage = CommentsStorage.getInstance();
    ReactionsStorage reactionsStorage = ReactionsStorage.getInstance();

    public PostDto getContent(UUID postId) {
        return null;
    }
}
