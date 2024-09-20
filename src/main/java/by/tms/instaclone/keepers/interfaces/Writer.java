package by.tms.instaclone.keepers.interfaces;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static by.tms.instaclone.keepers.KeeperConstants.ERROR_IO_FILE_TEMPLATE;
import static by.tms.instaclone.keepers.KeeperConstants.ERROR_TEMPLATE;
import static by.tms.instaclone.utilites.MyLogger.logIn;

public interface Writer {

    void write(Object object);

    /**
     * Метод производит запись в nameFile одной(!) строки (свойства Сущности, например)
     *
     * @param nameFile - имя файла (с путём), в который производится сохранение строки
     * @param rowText - сохраняемая строка (добавляется в хвост файла)
     *
     * запись в файл производится в отдельном потоке
     */
    static void threadWrite( String nameFile, String rowText) {
        Thread writeThread = new Thread(() -> {
            try {
                Files.write(Paths.get(nameFile), rowText.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException ex) {
// todo решить: логгер или исключение?
                logIn(ERROR_TEMPLATE.formatted(ERROR_IO_FILE_TEMPLATE.formatted(nameFile)));
            }
        });
        writeThread.start();
    }
}