/*package by.tms.instaclone.storage;

import by.tms.instaclone.model.Comment;
import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.Reaction;
import by.tms.instaclone.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

*//*import static by.tms.instaclone.storage.Deleter.deleteContentCsvFile;
import static by.tms.instaclone.storage.Reader.readCsvFile;
import static by.tms.instaclone.storage.Writer.writeCsvFile;*//*

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
        Optional<String> fileString = readCsvFile("csv/reactions.csv");
        if (!fileString.get().isEmpty()) {
            String[] arrayRows = fileString.get().split("\n");
            for (String row : arrayRows) {
                String[] arrayWords = row.split(";");
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

    public Reaction newReaction(Post post, User user, boolean newReaction) {
        Reaction reaction = new Reaction(post, user, newReaction);
        reactions.put(reaction.getUuid(), reaction);
        String reactionFormat = "%s;%s;%s;%s;%s;\n";
        String rowText = reactionFormat.formatted(reaction.getUuid().toString(),
                reaction.getAddressee().getUuid().toString(),
                reaction.getOwner().getUuid().toString(),
                String.valueOf(reaction.isTypeReaction()),
                reaction.getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()/1000);
        writeCsvFile("csv/reactions.csv", rowText);
        return reaction;
    }

    private void newReaction(Reaction reaction) {
        reactions.put(reaction.getUuid(), reaction);
        String reactionFormat = "%s;%s;%s;%s;\n";
        String rowText = reactionFormat.formatted(reaction.getUuid().toString(),
                reaction.getAddressee().getUuid().toString(),
                reaction.getOwner().getUuid().toString(),
                String.valueOf(reaction.isTypeReaction()),
                reaction.getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()/1000);
        writeCsvFile("csv/reactions.csv", rowText);
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
        for (Map.Entry entry: reactions.entrySet()) {
            if (((Reaction) entry.getValue()).getOwner().getUuid().equals(ownerUuid)) {
                reactionOwner.put(((Reaction) entry.getValue()).getUuid(), (Reaction) entry.getValue());
            }
        }
        return reactionOwner;
    }

    public Map<UUID, Reaction> getReactionPost(UUID postUuid) {
        Map<UUID, Reaction> reactionPost = new HashMap<>();
        for (Map.Entry entry: reactions.entrySet()) {
            if (((Reaction) entry.getValue()).getAddressee().getUuid().equals(postUuid)) {
                reactionPost.put(((Reaction) entry.getValue()).getUuid(), (Reaction) entry.getValue());
            }
        }
        return reactionPost;
    }

    public void changeReaction(Reaction reaction, boolean newTypeReaction) {
        Reaction newReaction = reactions.get(reaction.getUuid());
        newReaction.setTypeReaction(newTypeReaction);
        substitute(reaction, newReaction);
    }

    private void rewrite() {
        StringBuilder contentReactionsStorage = new StringBuilder();
        for (Map.Entry entry: reactions.entrySet()) {
            contentReactionsStorage.append(((Reaction) entry.getValue()).getUuid().toString()).append(";")
                    .append(((Reaction) entry.getValue()).getAddressee().getUuid().toString()).append(";")
                    .append(((Reaction) entry.getValue()).getOwner().getUuid().toString()).append(";")
                    .append(((Reaction) entry.getValue()).isTypeReaction()).append(";")
                    .append(((Reaction) entry.getValue()).getCreateAt().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()/1000).append(";")
                    .append("\n");
        }
        deleteContentCsvFile("csv/reactions.csv");
        writeCsvFile("csv/reactions.csv", contentReactionsStorage.toString());
    }

    private void substitute(Reaction oldReaction, Reaction newReaction) {
        deleteReaction(oldReaction);
        newReaction(newReaction);
    }

}*/
