package by.tms.instaclone.services.profileService;

import by.tms.instaclone.DTOs.ProfileDTO;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.SubscriptionsStorage;
import by.tms.instaclone.storage.UsersStorage;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class ProfileService {
    private final String usernameProfile;
    private final User userSession;

    public ProfileService(String usernameProfile, User user) {
        this.usernameProfile = usernameProfile;
        this.userSession = user;
    }

    public Optional<ProfileDTO> collectorProfile() {
        ProfileDTO profileDTO = new ProfileDTO(usernameProfile);
        User user = UsersStorage.getInstance().getUser(usernameProfile);
        profileDTO.setCountPost(PostsStorage.getInstance().getPostsOwner(user.getUuid()).size());
        profileDTO.setCountSubscriber(SubscriptionsStorage.getInstance().getPublishersFollower(user).size());
        profileDTO.setCountSubscription(SubscriptionsStorage.getInstance().getFollowersPublisher(user).size());
        HashMap<UUID, User> userSessionHashMap = SubscriptionsStorage.getInstance().getFollowersPublisher(userSession);
System.out.println(userSessionHashMap);
        if (userSession.getUsername().equals(usernameProfile)) {
            profileDTO.setStatusSubscription(0);
        } else {
            if (userSessionHashMap.containsKey(userSession.getUuid())) {
                profileDTO.setStatusSubscription(1);
            } else {
                profileDTO.setStatusSubscription(2);
            }
        }


        return Optional.of(profileDTO);
    }
}
