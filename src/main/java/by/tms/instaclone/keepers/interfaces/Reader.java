package by.tms.instaclone.keepers.interfaces;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static by.tms.instaclone.keepers.KeeperConstants.ERROR_IO_FILE_TEMPLATE;
import static by.tms.instaclone.keepers.KeeperConstants.ERROR_TEMPLATE;
import static by.tms.instaclone.utilites.MyLogger.logIn;

public interface Reader {

    List<?> read();                     // формирует Список из ВСЕХ строк
    List<?> readRow(String uuid);       // формирует Список только из строк, принадлежащих Сущности с UUID=uuid
    List<?> readRowsOwner(String uuid); // формирует Список только из строк, принадлежащих Владельцу с UUID=uuid

    /**
     * Метод производит чтение из nameFile
     *
     * @param nameFile - имя файла (с путём), из которого считываются данные, помещаемые в String
     * @return - возвращает указанный файл (nameFile) в виде строки
     */
    static Optional<String> readFile(String nameFile) {
        try {
            return Optional.ofNullable(Files.readString(Paths.get(nameFile)));
        } catch (IOException ex) {
// todo решить: логгер или исключение?
            logIn(ERROR_TEMPLATE.formatted(ERROR_IO_FILE_TEMPLATE.formatted(nameFile)));
            return Optional.empty();
        }
    }
}
