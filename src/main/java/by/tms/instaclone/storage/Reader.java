package by.tms.instaclone.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import static by.tms.instaclone.storage.KeeperConstants.ERROR_IO_FILE_TEMPLATE;
import static by.tms.instaclone.storage.KeeperConstants.ERROR_TEMPLATE;
import static by.tms.instaclone.utilites.SiteLogger.getLogger;

public class Reader {

    /**
     * Метод производит чтение из nameFile
     *
     * @param nameFile - имя файла (с путём), из которого считываются данные, помещаемые в String
     * @return - возвращает указанный файл (nameFile) в виде строки
     */
    public static Optional<String> readCsvFile(String nameFile) {
        try {
            return Optional.ofNullable(Files.readString(Paths.get(nameFile)));
        } catch (IOException ex) {
// todo решить: логгер или исключение?
            getLogger().addRecord(ERROR_TEMPLATE.formatted(ERROR_IO_FILE_TEMPLATE.formatted(nameFile)));
            return Optional.empty();
        }
    }
}
