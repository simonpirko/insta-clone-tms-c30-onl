package by.tms.instaclone.servlet;

import by.tms.instaclone.dto.PostDto;
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

@WebServlet("/user/post")
public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostService postService = new PostService();
        PostDto postDto = postService.getContent(UUID.fromString(req.getParameter("postUUID")));
        req.setAttribute("postDto", postDto);
        getServletContext().getRequestDispatcher(POST_JSP).forward(req, resp);
    }

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
