package by.tms.instaclone.storage;

import by.tms.instaclone.model.Photo;
import by.tms.instaclone.model.Post;

import static by.tms.instaclone.storage.KeeperConstants.*;
import static by.tms.instaclone.storage.Reader.readCsvFile;
import static by.tms.instaclone.storage.Writer.writeCsvFile;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class PhotoStorage {
    private ConcurrentHashMap<UUID, Photo> photos;
    private static PhotoStorage instance;

    public static PhotoStorage getInstance() {
        if (instance == null) {
            instance = new PhotoStorage();
        }
        return instance;
    }

    private PhotoStorage() {
        photos = new ConcurrentHashMap<>();
        Optional<String> fileString = readCsvFile(PHOTOS_CSV_FILE);
        if (fileString.get().length() > 0) {
            String[] arrayRows = fileString.get().split(LF);
            for (String row : arrayRows) {
                String[] arrayWords = row.split(SEPARATOR_CSV);
                Optional<byte[]> image = null;
                image = getBytePhoto(arrayWords[0], arrayWords[2]);
                image.ifPresent(bytes -> photos.put(UUID.fromString(arrayWords[0]),
                        new Photo(UUID.fromString(arrayWords[0]),
                                PostsStorage.getInstance().getPost(UUID.fromString(arrayWords[1])),
                                bytes,
                                arrayWords[2],
                                LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(arrayWords[3])), ZoneId.systemDefault()))));
            }
        }
    }

    public void addPhoto(Post post, Part image) throws IOException {
        String[] format = image.getContentType().split("/");
        if (format[0].equals("image")) {
            byte[] ImageInByte = image.getInputStream().readAllBytes();
            Photo photo = new Photo(post, ImageInByte, format[1]);
            photos.put(photo.getUuid(), photo);
            saveImage(photo.getUuid(), ImageInByte, format[1]);
            String rowText = PHOTO_CSV_FORMAT_TEMPLATE.formatted(photo.getUuid(), post.getUuid(), format[1], photo.getCreateAt());
            writeCsvFile(PHOTOS_CSV_FILE, rowText);

        } else {
            //Здесь покажем пользователю что файл не валиден.
        }
    }

    public Photo getPhoto(UUID uuid) {
        return photos.get(uuid);
    }

    public Map<UUID, Photo> getPhotoOfPost(UUID postUUID) {
        Map<UUID, Photo> photoOfPost = new HashMap<>();
        Stream<Photo> photoStream = photos.values().stream();
        photoStream.filter(photo -> photo.getPost().getUuid().equals(postUUID)).
                forEach(photo -> photoOfPost.put(photo.getUuid(), photo));
        return photoOfPost;
    }

    /**
     * Метод создаёт набор тех фотографий, которые включены в Post с UUID равным postUUID
     *
     * @param postUUID - UUID поста
     * @return - набор фотографий
     */
    public List<String> getPhotosPost(UUID postUUID) {
        List<String> photosPost = new ArrayList<>();
        for (Photo photo : photos.values()) {
            if (photo.getPost().getUuid().equals(postUUID)) {
                String uuidPhoto = photo.getUuid().toString();
                String formatPhoto = photo.getFormat();
                Optional<byte[]> imageInByte = getBytePhoto(uuidPhoto, formatPhoto);
                imageInByte.ifPresent(bytes -> photosPost.add(Base64.getEncoder().encodeToString(bytes)));
            }
        }
        return photosPost;
    }

    private void saveImage(UUID uuid, byte[] image, String format) {
        File imageFile = new File(PATH_TO_PHOTOS + uuid + "." + format);
        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            fos.write(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод передаёт байтовый набор фотографии, указанной через её имя и расширение
     *
     * @param photoID - имя фотографии
     * @param format - формат фотографии
     * @return - набор байтов фотографии
     */
    public Optional<byte[]> getBytePhoto(String photoID, String format) {
        Path pathToImage = Path.of(PATH_TO_PHOTOS.concat(photoID + "." + format));
        if (Files.exists(pathToImage)) {
            try {
                return Optional.ofNullable(Files.readAllBytes(pathToImage));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return Optional.empty();
        }
    }
}