package by.tms.instaclone;

import by.tms.instaclone.dto.PublisherCardDto;
import by.tms.instaclone.dto.UserHomePageDto;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.UsersStorage;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static by.tms.instaclone.storage.KeeperConstants.*;

public class UserHomePageDtoProbe {

    public static void main(String[] args) {
        User currentUser = UsersStorage.getInstance().getUser(UUID.fromString("aa44061c-62f5-37a9-b126-43053134c2d6"));
        UserHomePageDto pageDto = new UserHomePageDto(currentUser.getUsername());
        String nameCurrentUser = pageDto.getName();
        String usernameCurrentUser = pageDto.getUsername();
        List<PublisherCardDto> publishersCardsPages = pageDto.getPublishersCards();

//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_CREATE_POST_TEMPLATE);
//        List<LocalDateTime> dateTime = publishersCardsPages.get(0).getCreatePost();
//        List<String> dateTimeString = new ArrayList<>();
//        for (LocalDateTime date : dateTime) {
//            dateTimeString.add(date.format(dateTimeFormatter));
//        }

        int publishersCardsCount = publishersCardsPages.size();
    }
}
