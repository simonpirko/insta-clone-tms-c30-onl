package by.tms.instaclone.keepers.servants;

import by.tms.instaclone.keepers.interfaces.Reader;
import by.tms.instaclone.model.User;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.tms.instaclone.keepers.KeeperConstants.*;
import static by.tms.instaclone.keepers.interfaces.Reader.readFile;

public class UsersReader implements Reader {

    @Override
    public List<?> read() {                         // формирует Список из ВСЕХ строк
        return List.of();
    }

    @Override
    public List<?> readRow(String uuid) {           // формирует Список только из строк, принадлежащих Сущности с UUID=uuid
        return List.of();
    }

    @Override
    public List<User> readRowsOwner(String uuid) {   // формирует Список только из строк, принадлежащих Владельцу с UUID=uuid
        List<User> users = new ArrayList<>();
        Optional<String> fileString = readFile(USERS_CSV_FILE);
        if (fileString.isPresent()) {
            String[] setRow = fileString.get().split(LF);
            for (String row : setRow) {
                String[] setWords = row.split(SEPARATOR_CSV);
                if (setWords[0].equals(uuid)) {
// todo отдать в users его поля
//                    users.add(new User(setWords[0], setWords[1], setWords[2], setWords[3]));
                }
            }
        }
        return users;
    }
}