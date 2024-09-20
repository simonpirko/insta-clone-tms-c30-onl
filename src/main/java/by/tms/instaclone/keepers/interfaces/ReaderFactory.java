package by.tms.instaclone.keepers.interfaces;

import by.tms.instaclone.keepers.servants.UsersReaderFactory;

import static by.tms.instaclone.keepers.KeeperConstants.USERS_CSV_FILE;

public interface ReaderFactory {

    Reader createReader();

    /**
     * Фабрика производит Ридера для чтения свойств Сущности из nameFile
     *
     * @param nameFile - имя файла (с путём), по которому определяем, который Ридер нам нужен
     * @return
     */
    static ReaderFactory createReaderFactory(String nameFile) {
        if (USERS_CSV_FILE.contains(nameFile)) {
            return new UsersReaderFactory();
            // заготовка для других сущностей
        } /*else if (POSTS_CSV_FILE.contains(nameFile)) {
            return new PostsReaderFactory();
        }*/ else {
            throw new RuntimeException("'" + nameFile + "' is not a valid nameFile"); // todo доработать!
        }
    }
}