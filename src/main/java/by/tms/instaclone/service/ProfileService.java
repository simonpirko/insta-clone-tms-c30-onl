package by.tms.instaclone.service;

import by.tms.instaclone.dto.CardProfileDTO;
import by.tms.instaclone.dto.ProfileDTO;
import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.*;

import java.time.format.DateTimeFormatter;
import java.util.*;

import static by.tms.instaclone.storage.KeeperConstants.*;

public class ProfileService {


    public Optional<ProfileDTO> collectorProfile(String usernameProfile, User userSession) {
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
        //формирование постов
        List<CardProfileDTO> cardProfileDTOS = new ArrayList<>();
        List<Post> postList = PostsStorage.getInstance().getListPostsOwner(userProfile.getUuid());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_LOGGER_TEMPLATE);
        for (int i = 0; i < postList.size(); i++) {
            CardProfileDTO cardProfileDTO = new CardProfileDTO();
            Post post = postList.get(i);
            cardProfileDTO.setPostUUID(String.valueOf(post.getUuid()));
            cardProfileDTO.setTextPostProfile(post.getText());
            cardProfileDTO.setPhotosPost(PhotoStorage.getInstance().getPhotosPost(post.getUuid()));
            cardProfileDTO.setCreateAtPost(post.getCreateAt().format(dateTimeFormatter));
            cardProfileDTO.setCarouselName("Carousel" + "-" + post.getUuid());
            cardProfileDTOS.add(cardProfileDTO);
        }
        profileDTO.setCardProfileDTOS(cardProfileDTOS);
        PhotoStorage photoStorage = PhotoStorage.getInstance();
        Optional<byte[]> avatar = null;
        avatar = photoStorage.getByteAvatar(userProfile.getUuid().toString());
        if (avatar.isPresent()) {
            String avatarka = Base64.getEncoder().encodeToString(avatar.get());
            profileDTO.setAvatar(avatarka);
        } else {
            avatar = photoStorage.getByteAvatar("DefaultAvatar");
            String avatarka = Base64.getEncoder().encodeToString(avatar.get());
            profileDTO.setAvatar(avatarka);
        }
        return Optional.of(profileDTO);
    }
}
