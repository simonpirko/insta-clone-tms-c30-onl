package by.tms.instaclone.servlet;

import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.PhotoStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
@MultipartConfig
@WebServlet(value = "/profile/*")
public class TestServlet extends HttpServlet {

PhotoStorage storage = PhotoStorage.getInstance();
User newUser = new User("alex","Alex","alex");
Post newPost = new Post(newUser,"first post");
        protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            String[] nameURL = req.getRequestURI().split("/");
            System.out.println(nameURL[2]);
            getServletContext().getRequestDispatcher("/pages/testPostStorage.jsp").forward(req, res);
        }
        protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            Part photo = req.getPart("photo");
            storage.addPhoto(newPost,photo);
            //res.sendRedirect("/profile");
        }
}
