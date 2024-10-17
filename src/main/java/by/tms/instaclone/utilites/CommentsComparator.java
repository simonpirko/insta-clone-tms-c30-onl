package by.tms.instaclone.utilites;

import by.tms.instaclone.model.Comment;

import java.util.Comparator;

public class CommentsComparator implements Comparator<Comment> {

    @Override
    public int compare(Comment c1, Comment c2) {
        return c2.getCreateAt().compareTo(c1.getCreateAt());
    }
}
