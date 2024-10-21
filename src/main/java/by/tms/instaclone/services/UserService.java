package by.tms.instaclone.services;

import by.tms.instaclone.DTOs.UserDTO;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.PhotoStorage;
import by.tms.instaclone.storage.UsernamesStorage;
import by.tms.instaclone.storage.UsersStorage;

import java.util.Base64;
import java.util.Optional;

import static by.tms.instaclone.storage.KeeperConstants.*;

public class UserService {
    private UserService() {}
    public static UserService getInstance() {
        return new UserService();
    }

    public Optional<UserDTO> getUserByUsername(String username) {
       try {
           Optional<User> u = Optional.ofNullable(UsersStorage.getInstance().getUser(username));
           UserDTO UserDTO = new UserDTO();
           if (u.isPresent()) {
               User user = u.get();
               UserDTO.setUsername(username);
               UserDTO.setName(user.getName());
               UserDTO.setUrlUser(USER_PROFILE_URL + SLAGE + username);
               Optional<byte[]> image = PhotoStorage.getInstance().
                       getByteAvatar(UsernamesStorage.getInstance().
                               getUUID(username).toString());
               byte[] avatarBytes = image.orElse(PhotoStorage.getInstance().getByteAvatar("DefaultAvatar").get());
               String avatar = Base64.getEncoder().encodeToString(avatarBytes);
               UserDTO.setAvatar(avatar);
               return Optional.of(UserDTO);
           }
       }
       catch (Exception e) {
           return Optional.empty();
       }

        return Optional.empty();
    }
}
