package by.tms.instaclone.keepers.servants;

import by.tms.instaclone.keepers.interfaces.Reader;
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

public class UsersReader implements Reader {

    // для User этод метод заглушен
    @Override
    public List<?> read() {
        // todo List.of() или throw new UnsupportedOperationException
        return List.of();
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Метод формирует набор Сущностей User.
     * Набор м.б. пустым или с одним элементом (если выполняются свойства консистентности :)
     *
     * @param uuid - идентификатор User
     * @return - List<User>
     */
    @Override
    public List<?> readRow(String uuid) {           // формирует Список User только из строк, где в строке "слово" UUID=uuid
        List<User> users = new ArrayList<>();
        Optional<String> fileString = readFile(USERS_CSV_FILE);
        if (fileString.isPresent()) {
            String[] setRow = fileString.get().split(LF);   // делим csv-файл на строки по LF - новая строка
            for (String row : setRow) {
                String[] kitWords = row.split(SEPARATOR_CSV);   // делим строку на "слова" по SEPARATOR_CSV
                if (kitWords[0].equals(uuid)) {
                    users.add(new User(UUID.fromString(kitWords[0]),
                            kitWords[1],
                            kitWords[2],
                            kitWords[3],
                            LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(kitWords[4])), ZoneId.systemDefault())));
                }
            }
        }
        return users;   // возвращаем список Users (м.б. пустым, т.к. по указанному uuid может ничего не найтись)
    }

    // для User этод метод заглушен
    @Override
    public List<User> readRowsOwner(String uuid) {   // формирует Список только из строк, принадлежащих Владельцу с UUID=uuid
        // todo List.of() или throw new UnsupportedOperationException
        return List.of();
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}