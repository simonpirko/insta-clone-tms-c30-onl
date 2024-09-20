package by.tms.instaclone.keepers.servants;

import by.tms.instaclone.keepers.interfaces.Reader;
import by.tms.instaclone.keepers.interfaces.ReaderFactory;
import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static by.tms.instaclone.keepers.KeeperConstants.*;
import static by.tms.instaclone.keepers.interfaces.Reader.readFile;
import static by.tms.instaclone.keepers.interfaces.ReaderFactory.createReaderFactory;

public class PostsReader implements Reader {

    /**
     * Метод формирует Список ВСЕХ Post
     *
     * @return List<Post> - список ВСЕХ постов, хранящихся в posts.csv
     */
    @Override
    public List<?> read() {
        List<Post> posts = new ArrayList<>();
        Optional<String> fileString = readFile(POSTS_CSV_FILE);
        if (fileString.isPresent()) {
            String[] setRow = fileString.get().split(LF);   // делим csv-файл на строки по LF ("перевод каретки")
            for (String row : setRow) {
                String[] kitWords = row.split(SEPARATOR_CSV);   // делим строку на "слова" по SEPARATOR_CSV
                ReaderFactory readerFactory = createReaderFactory(USERS);
                Reader reader = readerFactory.createReader();
                List<?> users = reader.readRow(kitWords[1]);
                posts.add(new Post(UUID.fromString(kitWords[0]),
                        (User) users.get(0),
                        kitWords[2],
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(kitWords[3])), ZoneId.systemDefault())));
            }
        }
        return posts;   // возвращаем список Post (м.б. пустым, если нет постов)
    }

    @Override
    public List<?> readRow(String uuid) {
        return List.of();
    }

    @Override
    public List<?> readRowsOwner(String uuid) {
        return List.of();
    }
}
