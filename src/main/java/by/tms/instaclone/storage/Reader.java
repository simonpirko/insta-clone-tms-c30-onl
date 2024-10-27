package by.tms.instaclone.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

import static by.tms.instaclone.storage.KeeperConstants.ERROR_IO_FILE_TEMPLATE;
import static by.tms.instaclone.storage.KeeperConstants.ERROR_TEMPLATE;
import static by.tms.instaclone.utilites.SiteLogger.getLogger;

public class Reader {

    /**
     * Метод производит чтение из nameFile
     *
     * @param nameFile - путь/имя файла, из которого считываются данные, помещаемые в String
     * @return - возвращает содержимое файл в виде строки
     */
    public static Optional<String> readCsvFile(String nameFile) {
        // todo пробую решить проблему с путями файла
          ClassLoader classLoader = Reader.class.getClassLoader();
//        String fileName = "db/user.csv";
        File csvFile = new File(Objects.requireNonNull(classLoader.getResource(nameFile)).getFile());
        //File csvFile = new File(Reader.class.getProtectionDomain().getCodeSource().getLocation().getPath().concat(nameFile));
        // todo обработать случай, когда файла не существует - создать его
        //
        try {
//            return Optional.ofNullable(Files.readString(Paths.get(nameFile)));
            return Optional.ofNullable(Files.readString(Paths.get(csvFile.toString())));
        } catch (IOException ex) {
// todo решить: логгер или исключение?
            getLogger().addRecord(ERROR_TEMPLATE.formatted(ERROR_IO_FILE_TEMPLATE.formatted(nameFile)));
            return Optional.empty();
        }
    }
}
