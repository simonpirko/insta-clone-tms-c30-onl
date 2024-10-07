package by.tms.instaclone.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static by.tms.instaclone.storage.KeeperConstants.ERROR_IO_FILE_TEMPLATE;
import static by.tms.instaclone.storage.KeeperConstants.ERROR_TEMPLATE;
import static by.tms.instaclone.utilites.SiteLogger.getLogger;

public class Deleter {

    /**
     * Метод производит удаление содержимого в указанном nameFile
     * @param nameFile  - путь/имя файла
     */
    public static void deleteContentCsvFile(String nameFile) {
        // todo пробую решить проблему с путями файла
        ClassLoader classLoader = Deleter.class.getClassLoader();    // todo с таким решением работа идёт с файлами из target
        File csvFile = new File(Objects.requireNonNull(classLoader.getResource(nameFile)).getFile());
        //
        try {
            new FileWriter(csvFile, StandardCharsets.UTF_8).close();
        } catch (IOException ex) {
// todo решить: логгер или исключение?
            getLogger().addRecord(ERROR_TEMPLATE.formatted(ERROR_IO_FILE_TEMPLATE.formatted(nameFile)));
        }
    }

}