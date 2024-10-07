package by.tms.instaclone.servlet;

import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.PhotoStorage;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.UsernamesStorage;
import by.tms.instaclone.storage.UsersStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile/*")
public class TestProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
String profileName = req.getPathInfo().substring(1);
System.out.println(profileName);
      User user = UsersStorage.getInstance().getUser(UsernamesStorage.getInstance().getUUID(profileName));

        PhotoStorage.getInstance().getPhoto(user.getUuid());

        getServletContext().getRequestDispatcher("/pages/testProfile.jsp").forward(req, res);
    }
}
