package by.tms.instaclone.utilites;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static by.tms.instaclone.keepers.KeeperConstants.DATE_TIME_LOGGER_TEMPLATE;
import static by.tms.instaclone.keepers.KeeperConstants.LOGGER_MESSAGE_TEMPLATE;

/**
 * Класс отвечает за ведение лога.
 * Логирование ведётся по времени UTC
 * Всегда существует только один экземпляр класса
 */
public class SiteLogger {
    private static SiteLogger siteLogger;

    public static synchronized SiteLogger getLogger() {
        if (siteLogger == null) {
            siteLogger = new SiteLogger();
        }
        return siteLogger;
    }

//    private SiteLogger() {}


    /**
     * Метод логирует некоторые события в лог Каталины.
     * Метод в СВОЁМ формате формирует дату-время и прсоединяет их к сообщению
     *
     * @param messageCustomer   - сообщение для логирования
     */
    // todo сделать логирование в отдельный файл!
    public void addRecord(String messageCustomer) {
        System.out.printf(LOGGER_MESSAGE_TEMPLATE.formatted(getStringDateTime(), messageCustomer));
    }

    public static String getStringDateTime() {
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_LOGGER_TEMPLATE);
        return dateTime.format(dateTimeFormatter);
    }
}
