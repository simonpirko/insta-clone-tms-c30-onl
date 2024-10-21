package by.tms.instaclone.servlet;

import by.tms.instaclone.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static by.tms.instaclone.storage.KeeperConstants.*;

@WebServlet(USER_POST_DELETE_PATH)
public class PostDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostService postService = new PostService();
        String postUUID = req.getParameter("postUUID");
        postService.deletePost(UUID.fromString(postUUID));
        resp.sendRedirect(USER_HOME_URL);
    }
}