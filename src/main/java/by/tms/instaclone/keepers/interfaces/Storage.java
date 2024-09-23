package by.tms.instaclone.keepers.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Storage {
    void appendStorage(Object object);
    void removeStorage(Object object);
    boolean containsStorage(Object object);
    List<?> getStorage();
    Optional<Object> getEntity(UUID uuid);
}