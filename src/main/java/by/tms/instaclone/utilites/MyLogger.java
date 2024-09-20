package by.tms.instaclone.utilites;

// todo сделать логирование в отдельный файл!

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static by.tms.instaclone.keepers.KeeperConstants.DATE_TIME_LOGGER_TEMPLATE;
import static by.tms.instaclone.keepers.KeeperConstants.LOGGER_MESSAGE_TEMPLATE;

public class MyLogger {
    public static void logIn(String messageCustomer) {
        System.out.printf(LOGGER_MESSAGE_TEMPLATE.formatted(getStringDateTime(), messageCustomer));
    }

    public static String getStringDateTime() {
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_LOGGER_TEMPLATE);
        return dateTime.format(dateTimeFormatter);
    }
}
