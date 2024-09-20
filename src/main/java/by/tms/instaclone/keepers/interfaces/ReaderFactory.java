package by.tms.instaclone.keepers.interfaces;

import by.tms.instaclone.keepers.servants.UsersReaderFactory;

import static by.tms.instaclone.keepers.KeeperConstants.POSTS_CSV_FILE;
import static by.tms.instaclone.keepers.KeeperConstants.USERS_CSV_FILE;

public interface ReaderFactory {

    Reader createReader();

    static ReaderFactory createReaderFactory(String nameFile) {
        if (USERS_CSV_FILE.contains(nameFile)) {
            return new UsersReaderFactory();
        } /*else if (POSTS_CSV_FILE.contains(nameFile)) {
            return new PostsReaderFactory();
        }*/ else {
            throw new RuntimeException("'" + nameFile + "' is not a valid nameFile"); // todo доработать!
        }
    }
}