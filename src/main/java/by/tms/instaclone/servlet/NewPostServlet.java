package by.tms.instaclone.servlet;

import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.PhotoStorage;
import by.tms.instaclone.storage.PostsStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;
import java.util.Collection;

import static by.tms.instaclone.storage.KeeperConstants.*;

@WebServlet(value = USER_NEW_POST_PATH)
@MultipartConfig
public class NewPostServlet extends HttpServlet {

    PostsStorage postsStorage = PostsStorage.getInstance();
    PhotoStorage photoStorage = PhotoStorage.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(NEW_POST_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User curUser = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
        Collection<Part> parts = req.getParts();
        String textPost = req.getParameter("textPost");
        int count = 0;
        for (Part part : parts) {
            count++;
            if (count >= 7) {
                req.setAttribute("errorMax", "Maximum 5 photos per post");
                req.getRequestDispatcher(NEW_POST_JSP).forward(req, resp);
            }
        }
        Post post = postsStorage.newPost(curUser, textPost);
        for (Part part : parts) {
            if (part.getName().equals("photosMultiple")) {
                photoStorage.addPhoto(post, part);
            }
        }
        resp.sendRedirect(USER_HOME_URL);
    }
}
