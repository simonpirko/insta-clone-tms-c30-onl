package by.tms.instaclone.service;

import by.tms.instaclone.dto.RefactorProfileDTO;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.PhotoStorage;
import by.tms.instaclone.storage.UsersStorage;

import java.util.Base64;
import java.util.Optional;

public class RefactorProfileService {
    public Optional<RefactorProfileDTO> collectDataProfile(String username) {
        RefactorProfileDTO refactorProfileDTO = new RefactorProfileDTO();
        refactorProfileDTO.setUsername(username);
        refactorProfileDTO.setProfileName(UsersStorage.getInstance().getUser(username).getName());
        User user = UsersStorage.getInstance().getUser(username);
        PhotoStorage photoStorage = PhotoStorage.getInstance();
        Optional<byte[]> avatar = null;
        avatar = photoStorage.getByteAvatar(user.getUuid().toString());
        if (avatar.isPresent()) {
            String avatarka = Base64.getEncoder().encodeToString(avatar.get());
            refactorProfileDTO.setAvatar(avatarka);
        } else {
            avatar = photoStorage.getByteAvatar("DefaultAvatar");
            String avatarka = Base64.getEncoder().encodeToString(avatar.get());
            refactorProfileDTO.setAvatar(avatarka);
        }
        return Optional.of(refactorProfileDTO);
    }


}
