package by.tms.instaclone.keepers.servants;

import by.tms.instaclone.keepers.interfaces.Writer;
import by.tms.instaclone.model.User;

import java.time.ZoneOffset;

import static by.tms.instaclone.keepers.KeeperConstants.USERS_CSV_FILE;
import static by.tms.instaclone.keepers.KeeperConstants.USERS_CSV_FORMAT_TEMPLATE;

public class GeneralWriter implements Writer {
    // todo имплементировать поля сущностей Post, Comment, Reaction, Subscription
    // todo Может, задавать в качестве параметра имя файла, в который производится сохранение?

    /**
     * Метод записывает набор свойств переданной Сущности в её файл
     *
     * @param object - Сущность (Post, Comment, Reaction, Subscription и т.п.), свойства которых надо сохранить в файле
     */
    @Override
    public void write(Object object) {
        String nameFile;
        String rowText;
        if (object instanceof User user) {
            // User раскладыватся на свойства с преобразованием объектов в String
            rowText = String.format(USERS_CSV_FORMAT_TEMPLATE, // константа хранит формат записи свойств User
                    user.getUuid().toString(),  // UUID преобразуется в строку
                    user.getName(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()/1000);    // LocalDateTime преобразуется в секунды
            nameFile = USERS_CSV_FILE;  // передается имя файла, где сохраняется User
        } // далее идут заготовки для других сущностей
        /*else if (object instanceof User post) {
            rowText = String.format(USERS_CSV_FORMAT_TEMPLATE,    // todo поменять константу и поля!
                    post.getUuidUser(),
                    post.getNameUser(),
                    post.getPassword());
            file = USERS_CSV_FILE;    // todo поменять константу!
        } else if (object instanceof User comment) {
            rowText = String.format(USERS_CSV_FORMAT_TEMPLATE,    // todo поменять константу и поля!
                    comment.getUuidUser(),
                    comment.getNameUser(),
                    comment.getPassword());
            file = USERS_CSV_FILE;    // todo поменять константу!
        } else if (object instanceof User reaction) {
            rowText = String.format(USERS_CSV_FORMAT_TEMPLATE,    // todo поменять константу и поля!
                    reaction.getUuidUser(),
                    reaction.getNameUser(),
                    reaction.getPassword());
            file = USERS_CSV_FILE;    // todo поменять константу!
        } else if (object instanceof User subscription) {
            rowText = String.format(USERS_CSV_FORMAT_TEMPLATE,    // todo поменять константу и поля!
                    subscription.getUuidUser(),
                    subscription.getNameUser(),
                    subscription.getPassword());
            file = USERS_CSV_FILE;    // todo поменять константу!
        }*/ else {
            // todo сделать запись в Лог, что переданный объект не подлежит сохранению (бросить Исключение?)
            return;
        }
        Writer.threadWrite(nameFile, rowText); // сформированная строка передаётся в файл на запись
    }
}