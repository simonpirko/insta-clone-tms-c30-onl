package by.tms.instaclone.service;

import by.tms.instaclone.dto.PostDto;
import by.tms.instaclone.dto.UserDTO;
import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.UsersStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SearchService {
    private static SearchService searchService;

    public static SearchService getInstance() {
        if (searchService == null) {
            searchService = new SearchService();
        }
        return searchService;
    }

    public List<UserDTO> searchUsers(String query) {
        List<UserDTO> users = new ArrayList<>();
        UserService userService = new UserService();
        ConcurrentHashMap<UUID, User> userMap = UsersStorage.getInstance().getUsers();
        for (UUID uuid : userMap.keySet()) {
            User user = userMap.get(uuid);
            if (user.getName().toLowerCase().contains(query.toLowerCase())
                    || user.getUsername().toLowerCase().contains(query.toLowerCase())) {
                users.add(userService.getUserByUsername(user.getUsername()).get());
            }
        }
        return users;
    }
    public List<PostDto> searchPosts(String query) {
        List<PostDto> posts = new ArrayList<>();
        PostService postService = new PostService();
        PostsStorage postsStorage = PostsStorage.getInstance();
        ConcurrentHashMap<UUID,Post> postMap = postsStorage.getPosts();
        for (UUID uuid : postMap.keySet()) {
            Post post = postMap.get(uuid);
            if(     post.getOwner().getUsername().toLowerCase().contains(query.toLowerCase()) ||
                    post.getOwner().getName().toLowerCase().contains(query.toLowerCase()) ||
                    post.getText().toLowerCase().contains(query.toLowerCase())) {
        posts.add(postService.getContent(post.getUuid()));
            }
        }
        return posts;
    }
    public List<PostDto> updateDTO(List<PostDto> posts) {
        List<PostDto> updatedPosts = new ArrayList<>();
        PostService postService = new PostService();
        for (PostDto postDto : posts) {
           updatedPosts.add(postService.getContent(postDto.getPostUUID()));
        }
       return updatedPosts;
    }
}
