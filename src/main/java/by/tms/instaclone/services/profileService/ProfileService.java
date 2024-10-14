package by.tms.instaclone.services.profileService;

import by.tms.instaclone.DTOs.ProfileDTO;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.SubscriptionsStorage;
import by.tms.instaclone.storage.UsersStorage;

import java.util.Optional;

public class ProfileService {
    private final String usernameProfile;
    private final User userSession;

    public ProfileService(String usernameProfile, User user) {
        this.usernameProfile = usernameProfile;
        this.userSession = user;
    }

    public Optional<ProfileDTO> collectorProfile() {
        ProfileDTO profileDTO = new ProfileDTO(usernameProfile);
        User userProfile = UsersStorage.getInstance().getUser(usernameProfile);
        profileDTO.setCountPost(PostsStorage.getInstance().getPostsOwner(userProfile.getUuid()).size());
        profileDTO.setCountSubscriber(SubscriptionsStorage.getInstance().getPublishersFollower(userProfile).size());
        profileDTO.setCountSubscription(SubscriptionsStorage.getInstance().getFollowersPublisher(userProfile).size());
        if (userSession.getUsername().equals(usernameProfile)) {
            profileDTO.setStatusSubscription(0);
        } else {
            if (SubscriptionsStorage.getInstance().isSubscription(userSession.getUuid(), userProfile.getUuid())) {
                profileDTO.setStatusSubscription(2);
            } else {
                profileDTO.setStatusSubscription(1);
            }
        }
        return Optional.of(profileDTO);
    }
}
