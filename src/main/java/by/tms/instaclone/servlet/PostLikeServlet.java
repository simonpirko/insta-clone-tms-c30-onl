package by.tms.instaclone.servlet;

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

@WebServlet(USER_POST_LIKE_PATH)
public class PostLikeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User curUser = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
        PostService postService = new PostService();
        String postUUID = req.getParameter("postUUID");
        postService.reactionPost(UUID.fromString(postUUID), curUser, true);
        resp.sendRedirect(USER_POST_PATH + "?postUUID=" + String.valueOf(postUUID));
    }
}
