package by.tms.instaclone.storage;

import by.tms.instaclone.model.Comment;
import by.tms.instaclone.model.Photo;
import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.User;
import by.tms.instaclone.utilites.Adapter;

import static by.tms.instaclone.storage.Deleter.deleteContentCsvFile;
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
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class PhotoStorage {
    private ConcurrentHashMap<UUID, Photo> photos;
    private static PhotoStorage instance;
    private Adapter adapter = new Adapter(PATH_TO_PHOTOS);

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
                image = getBytePhoto(arrayWords[0]);
                image.ifPresent(bytes -> photos.put(UUID.fromString(arrayWords[0]),
                        new Photo(UUID.fromString(arrayWords[0]),
                                PostsStorage.getInstance().getPost(UUID.fromString(arrayWords[1])),
                                bytes,
                                LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(arrayWords[2])), ZoneId.systemDefault()))));
            }
        }
    }

    public void addPhoto(Post post, Part image) throws IOException {
        byte[] ImageInByte = image.getInputStream().readAllBytes();
        Photo photo = new Photo(post, ImageInByte);
        photos.put(photo.getUuid(), photo);
        saveImage(photo.getUuid(), ImageInByte);
        String rowText = PHOTO_CSV_FORMAT_TEMPLATE.formatted(photo.getUuid(), post.getUuid(), photo.getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()/1000);
        writeCsvFile(PHOTOS_CSV_FILE, rowText);
    }

    public void addAvatar(User user, Part image) throws IOException {
        String[] format = image.getContentType().split("/");
        if (format[0].equals("image")) {
            byte[] ImageInByte = image.getInputStream().readAllBytes();
            saveImage(user.getUuid(), ImageInByte);
        } else {
            //Здесь покажем пользователю что файл не валиден.
        }
    }

    public Photo getPhoto(UUID uuid) {
        return photos.get(uuid);
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
                Optional<byte[]> imageInByte = getBytePhoto(uuidPhoto);
                imageInByte.ifPresent(bytes -> photosPost.add(Base64.getEncoder().encodeToString(bytes)));
            }
        }
        return photosPost;
    }

    public List<Photo> getAllPhotosPost(UUID postUUID) {
        List<Photo> photosPost = new ArrayList<>();
        for (Map.Entry entry: photos.entrySet()) {
            if (((Photo) entry.getValue()).getPost().getUuid().equals(postUUID)) {
                photosPost.add((Photo) entry.getValue());
            }
        }
        return photosPost;
    }

    private void saveImage(UUID uuid, byte[] image) {
        //File imageFile = new File(adapter.getPathToOs() + uuid + "." + format);
        File imageFile = new File(adapter.getPathToOs() + uuid);
        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            fos.write(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает байтовый набор фотографии, указанной через её имя и расширение
     *
     * @param photoID - имя фотографии
     * @return - набор байтов фотографии
     */
    public Optional<byte[]> getBytePhoto(String photoID) {

         Path pathToImage = Path.of(adapter.getPathToOs().concat(photoID));
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

    public Optional<byte[]> getByteAvatar(String userUUID) {  //Временное очень печальное решение
    String photoID = userUUID.toString();

        if (Files.exists(Path.of(adapter.getPathToOs().concat(photoID)))) {

            try {
                return Optional.ofNullable(Files.readAllBytes(Path.of(adapter.getPathToOs().concat(photoID))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return Optional.empty();
        }
    }

    public void deletePhoto(Photo photo) {
        photos.remove(photo.getUuid());
        File imageFile = new File(adapter.getPathToOs() + photo.getUuid());
        imageFile.delete();
        rewrite();
    }

    private void rewrite() {
        StringBuilder contentPhotosStorage = new StringBuilder();
        for (Map.Entry entry: photos.entrySet()) {
            contentPhotosStorage.append(((Photo) entry.getValue()).getUuid().toString()).append(SEPARATOR_CSV)
                    .append(((Photo) entry.getValue()).getPost().getUuid().toString()).append(SEPARATOR_CSV)
                    .append(((Photo) entry.getValue()).getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()/1000).append(SEPARATOR_CSV)
                    .append(LF);
        }
        deleteContentCsvFile(PHOTOS_CSV_FILE);
        writeCsvFile(PHOTOS_CSV_FILE, contentPhotosStorage.toString());
    }
}
