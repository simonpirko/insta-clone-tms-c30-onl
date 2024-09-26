package by.tms.instaclone.utilites;

import by.tms.instaclone.storage.Writer;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static by.tms.instaclone.storage.KeeperConstants.*;

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

    /**
     * Метод логирует необходимые события в лог Каталины.
     * Метод в СВОЁМ формате формирует дату-время и присоединяет их к сообщению
     *
     * @param messageCustomer   - сообщение для логирования
     */
    public void addRecord(String messageCustomer) {
        String record = LOGGER_MESSAGE_TEMPLATE.formatted(getStringDateTime(), messageCustomer);
        if (IS_PERFORM_FILE_LOGGING) {
            Writer.writeCsvFile(LOGS_FILE, record);
        } else {
            System.out.printf(record);
        }
    }

    public static String getStringDateTime() {
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_LOGGER_TEMPLATE);
        return dateTime.format(dateTimeFormatter);
    }
}
