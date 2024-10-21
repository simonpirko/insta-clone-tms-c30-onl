package by.tms.instaclone.storage;

import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.Reaction;
import by.tms.instaclone.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static by.tms.instaclone.storage.Deleter.deleteContentCsvFile;
import static by.tms.instaclone.storage.KeeperConstants.*;
import static by.tms.instaclone.storage.Reader.readCsvFile;
import static by.tms.instaclone.storage.Writer.writeCsvFile;

public class ReactionsStorage {

    private static ReactionsStorage reactionsStorage;
    private ConcurrentHashMap<UUID, Reaction> reactions;

    public static synchronized ReactionsStorage getInstance() {
        if (reactionsStorage == null) {
            reactionsStorage = new ReactionsStorage();
        }
        return reactionsStorage;
    }

    private ReactionsStorage() {
        reactions = new ConcurrentHashMap<>();
        Optional<String> fileString = readCsvFile(REACTIONS_CSV_FILE);
        if (!fileString.get().isEmpty()) {
            String[] arrayRows = fileString.get().split(LF);
            for (String row : arrayRows) {
                String[] arrayWords = row.split(SEPARATOR_CSV);
                reactions.put(UUID.fromString(arrayWords[0]), new Reaction(UUID.fromString(arrayWords[0]),
                        PostsStorage.getInstance().getPost(UUID.fromString(arrayWords[1])),
                        UsersStorage.getInstance().getUser(UUID.fromString(arrayWords[2])),
                        Boolean.parseBoolean(arrayWords[3]),
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(arrayWords[4])), ZoneId.systemDefault())));
            }
        }
    }

    public ConcurrentHashMap<UUID, Reaction> getReactions() {
        return reactions;
    }

    /**
     * Метод вводит в оборот новую Реакцию на Пост со стороны Пользователя. При этом, если раньше уже
     * существовала Реакция данного Пользователя на данный Пост,
     * то старая Реакция ЗАМЕЩАТСЯ новой - старается удаляется, а новая заводится в Систему
     * @param post - объект Поста
     * @param user - объект Пользователя
     * @param newReaction - тип Реакции Пользователя на Пост
     * @return - новая Реакция-объект
     */
    public Reaction newReaction(Post post, User user, boolean newReaction) {
        Optional<UUID> uuid = findUuidReaction(post.getUuid(),user.getUuid());    // найти юдь Реакции Пользователя на указанный Пост
        if (uuid.isPresent()) { // если какая-то Реакия уже сущетсвует,
            deleteReaction(getReaction(uuid.get()));    // то снести её!
        }
        Reaction reaction = new Reaction(post, user, newReaction);
        reactions.put(reaction.getUuid(), reaction);
        String rowText = REACTIONS_CSV_FORMAT_TEMPLATE.formatted(reaction.getUuid().toString(),
                reaction.getAddressee().getUuid().toString(),
                reaction.getOwner().getUuid().toString(),
                String.valueOf(reaction.isTypeReaction()),
                reaction.getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() / 1000);
        writeCsvFile(REACTIONS_CSV_FILE, rowText);
        return reaction;
    }

    private void newReaction(Reaction reaction) {
        reactions.put(reaction.getUuid(), reaction);
        String rowText = REACTIONS_CSV_FORMAT_TEMPLATE.formatted(reaction.getUuid().toString(),
                reaction.getAddressee().getUuid().toString(),
                reaction.getOwner().getUuid().toString(),
                String.valueOf(reaction.isTypeReaction()),
                reaction.getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() / 1000);
        writeCsvFile(REACTIONS_CSV_FILE, rowText);
    }

    public void deleteReaction(Reaction reaction) {
        reactions.remove(reaction.getUuid());
        rewrite();
    }

    public Reaction getReaction(UUID uuid) {
        return reactions.get(uuid);
    }

    public Map<UUID, Reaction> getReactionOwner(UUID ownerUuid) {
        Map<UUID, Reaction> reactionOwner = new HashMap<>();
        for (Map.Entry entry : reactions.entrySet()) {
            if (((Reaction) entry.getValue()).getOwner().getUuid().equals(ownerUuid)) {
                reactionOwner.put(((Reaction) entry.getValue()).getUuid(), (Reaction) entry.getValue());
            }
        }
        return reactionOwner;
    }

    public List<Reaction> getAllReactionPost(UUID postUuid) {
        List<Reaction> reactionPost = new ArrayList<>();
        for (Map.Entry entry : reactions.entrySet()) {
            if (((Reaction) entry.getValue()).getAddressee().getUuid().equals(postUuid)) {
                reactionPost.add((Reaction) entry.getValue());
            }
        }
        return reactionPost;
    }

    /**
     * Метод возвращает количество лайков у Поста, определямого через его UUID
     *
     * @param postUuid - UUID Поста
     * @return likeCount - количество лайков
     */
    public long getCountLikePost(UUID postUuid) {
        long likeCount = 0;
        for (Map.Entry entry : reactions.entrySet()) {
            if (((Reaction) entry.getValue()).getAddressee().getUuid().equals(postUuid)
                    && ((Reaction) entry.getValue()).isTypeReaction()) {
                likeCount++;
            }
        }
        return likeCount;
    }

    /**
     * Метод возвращает количество дизлайков у Поста, определямого через его UUID
     *
     * @param postUuid - UUID Поста
     * @return dislikeCount - количество лайков
     */
    public long getCountDislikePost(UUID postUuid) {
        long dislikeCount = 0;
        for (Map.Entry entry : reactions.entrySet()) {
            if (((Reaction) entry.getValue()).getAddressee().getUuid().equals(postUuid)
                    && !((Reaction) entry.getValue()).isTypeReaction()) {
                dislikeCount++;
            }
        }
        return dislikeCount;
    }

    /**
     * Метод возвращает тип Реакции на Пост, определяемый через postUuid, со стороны
     * Пользователя, определяемого через userUuid
     *
     * @param postUuid - UUID Поста
     * @param userUuid - UUID Пользователя
     * @return String - LIKE_REACTION - like, DISLIKE_REACTION - dislike, NONE_REACTION - нет реакции
     */
    public String seeReaction(UUID postUuid, UUID userUuid) {
        for (Map.Entry entry : reactions.entrySet()) {
            UUID postReactionUuid = ((Reaction) entry.getValue()).getAddressee().getUuid();
            UUID userReactionUuid = ((Reaction) entry.getValue()).getOwner().getUuid();
            if (postReactionUuid.equals(postUuid)
                    && userReactionUuid.equals(userUuid)) {
                if (((Reaction) entry.getValue()).isTypeReaction()) {
                    return LIKE_REACTION;
                } else {
                    return DISLIKE_REACTION;
                }
            }
        }
        return NONE_REACTION;
    }

    /**
     * Метод возвращает UUID Реакции, если уже существует Реакция на Пост, определённый через postUuid, со
     * стороны Пользователя, определённого через userUuid
     *
     * @param postUuid - UUID Поста
     * @param userUuid - UUID Пользователя
     * @return Optional<UUID>
     */
    public Optional<UUID> findUuidReaction(UUID postUuid, UUID userUuid) {
        for (Map.Entry entry : reactions.entrySet()) {
            UUID postReactionUuid = ((Reaction) entry.getValue()).getAddressee().getUuid();
            UUID userReactionUuid = ((Reaction) entry.getValue()).getOwner().getUuid();
            if (postReactionUuid.equals(postUuid)
                    && userReactionUuid.equals(userUuid)) {
                return Optional.ofNullable((UUID) entry.getKey());
            }
        }
        return Optional.empty();
    }

    public void changeReaction(Reaction reaction, boolean newTypeReaction) {
        Reaction newReaction = reactions.get(reaction.getUuid());
        newReaction.setTypeReaction(newTypeReaction);
        substitute(reaction, newReaction);
    }

    private void rewrite() {
        StringBuilder contentReactionsStorage = new StringBuilder();
        for (Map.Entry entry : reactions.entrySet()) {
            contentReactionsStorage.append(((Reaction) entry.getValue()).getUuid().toString()).append(SEPARATOR_CSV)
                    .append(((Reaction) entry.getValue()).getAddressee().getUuid().toString()).append(SEPARATOR_CSV)
                    .append(((Reaction) entry.getValue()).getOwner().getUuid().toString()).append(SEPARATOR_CSV)
                    .append(((Reaction) entry.getValue()).isTypeReaction()).append(SEPARATOR_CSV)
                    .append(((Reaction) entry.getValue()).getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() / 1000).append(SEPARATOR_CSV)
                    .append(LF);
        }
        deleteContentCsvFile(REACTIONS_CSV_FILE);
        writeCsvFile(REACTIONS_CSV_FILE, contentReactionsStorage.toString());
    }

    private void substitute(Reaction oldReaction, Reaction newReaction) {
        deleteReaction(oldReaction);
        newReaction(newReaction);
    }
}