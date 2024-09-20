package by.tms.instaclone.keepers.servants;

import by.tms.instaclone.keepers.interfaces.Reader;
import by.tms.instaclone.keepers.interfaces.ReaderFactory;

public class PostsReaderFactory implements ReaderFactory {

    @Override
    public Reader createReader() {
        return new PostsReader();
    }
}