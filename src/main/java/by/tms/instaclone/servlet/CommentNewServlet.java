package by.tms.instaclone.servlet;

import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.User;
import by.tms.instaclone.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

import static by.tms.instaclone.storage.KeeperConstants.*;
import static by.tms.instaclone.storage.KeeperConstants.USER_POST_PATH;

@WebServlet(USER_NEW_COMMENT_PATH)
public class CommentNewServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User curUser = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
        String commentText = req.getParameter("commentText");
        UUID postUUID = UUID.fromString(req.getParameter("postUUID"));
        if (commentText == null || commentText.isEmpty() || commentText.isBlank()) {
            resp.sendRedirect(USER_POST_PATH + "?postUUID=" + String.valueOf(postUUID));
        } else {
            PostService postService = new PostService();
            Post post = postService.setComment(postUUID, commentText, curUser);
            resp.sendRedirect(USER_POST_PATH + "?postUUID=" + post.getUuid());
        }
    }

}
