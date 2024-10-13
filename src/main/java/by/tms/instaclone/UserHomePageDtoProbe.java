package by.tms.instaclone;

import by.tms.instaclone.dto.PublisherCardLastPostDto;
import by.tms.instaclone.dto.UserHomePageDto;
import by.tms.instaclone.model.User;
import by.tms.instaclone.service.PublisherCardLastPostService;
import by.tms.instaclone.service.UserHomePageService;
import by.tms.instaclone.storage.UsersStorage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserHomePageDtoProbe {

    public static void main(String[] args) {
        String username = "qwe";
        PublisherCardLastPostService contentPublisherCard = new PublisherCardLastPostService(username);
        Optional<PublisherCardLastPostDto> dto = contentPublisherCard.collect();

        UserHomePageService content = new UserHomePageService(username);
        Optional<UserHomePageDto> pageDto = content.collect();
        User currentUser = UsersStorage.getInstance().getUser(UUID.fromString("aa44061c-62f5-37a9-b126-43053134c2d6"));
    }
}
