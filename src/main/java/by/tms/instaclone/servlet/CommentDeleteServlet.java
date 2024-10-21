package by.tms.instaclone.servlet;

import by.tms.instaclone.model.User;
import by.tms.instaclone.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tms.instaclone.storage.KeeperConstants.*;

@WebServlet(USER_COMMENT_DELETE_PATH)
public class CommentDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostService postService = new PostService();
        String commentUUID = req.getParameter("commentUUID");
        String postUUID = req.getParameter("postUUID");
        postService.deleteComment(commentUUID);
        resp.sendRedirect(USER_POST_PATH + "?postUUID=" + String.valueOf(postUUID));
    }
}
