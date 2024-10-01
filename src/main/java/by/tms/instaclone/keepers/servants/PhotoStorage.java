package by.tms.instaclone.keepers.servants;

import by.tms.instaclone.model.Photo;
import by.tms.instaclone.model.Post;
import static by.tms.instaclone.keepers.KeeperConstants.*;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class PhotoStorage {
    private ArrayList<UUID> photos = new ArrayList<UUID>();
    private static PhotoStorage instance;

    public static PhotoStorage getInstance() {
        if (instance == null) {
            instance = new PhotoStorage();
        }
        return instance;
    }

    private PhotoStorage() {
    }

    public void addPhoto(Post post, Part image) throws IOException {
        String[] format = image.getContentType().split("/");
        if (format[0].equals("image")) {
            byte[] ImageInByte = image.getInputStream().readAllBytes();
            Photo photo = new Photo(post, ImageInByte, format[1]);
            photos.add(photo.getUuid());
        } else {//Здесь сообщение что типа файла не валиден.
        }
    }
}