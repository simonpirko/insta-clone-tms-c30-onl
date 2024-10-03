package by.tms.instaclone.storage;

import by.tms.instaclone.model.Photo;
import by.tms.instaclone.model.Post;
import static by.tms.instaclone.storage.KeeperConstants.*;
import static by.tms.instaclone.storage.Writer.writeCsvFile;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PhotoStorage {
    private ConcurrentHashMap<UUID,Photo> photos = new ConcurrentHashMap<UUID,Photo>();
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
            photos.put (photo.getUuid(), photo);
            saveImage(photo.getUuid(),ImageInByte,format[1]);
            String rowText = PHOTO_CSV_FORMAT_TEMPLATE.formatted(photo.getUuid(),post.getUuid(),format[1],photo.getCreateAt());
            writeCsvFile(PHOTOS_CSV_FILE, rowText);
        } else {//Здесь сообщение что типа файла не валиден.
        }
    }

    public Optional<Object> getPhotos(UUID uuid) throws IOException {
        if (photos.get(uuid) != null) {
            Photo photo = photos.get(uuid);
            return Optional.of(photo);
        } else {
            return Optional.empty();
        }
    }
    private void saveImage(UUID uuid, byte[] image, String format) throws IOException {
        File imageFile = new File(PATH_TO_PHOTOS + uuid+"."+format);
        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            fos.write(image);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}