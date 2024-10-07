package by.tms.instaclone.servlet;

import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.PhotoStorage;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.UsersStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;

@MultipartConfig
@WebServlet(value = "/post")
public class TestPostServlet extends HttpServlet {
    PhotoStorage storage = PhotoStorage.getInstance();
    User CurrentUser = new User("Alex","alex","alex");
        protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            getServletContext().getRequestDispatcher("/pages/testPostStorage.jsp").forward(req, res);
        }
        protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            Collection<Part> parts = req.getParts();
            Post newPost = new Post(CurrentUser, req.getParameter("Text"));
            PostsStorage.getInstance().newPost(newPost);
            for (Part part : parts) {
                if (part.getName().equals("photo")) {
                    storage.addPhoto(newPost, part);
                }
            }
        }
}
