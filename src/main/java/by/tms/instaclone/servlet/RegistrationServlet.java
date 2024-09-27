package by.tms.instaclone.servlet;

import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.UsernamesStorage;
import by.tms.instaclone.storage.UsersStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static by.tms.instaclone.storage.UsersStorage.usersStorage;

@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //создать юзера,сохранить.
        UsersStorage usersStorage = UsersStorage.getInstance();
        ConcurrentHashMap<String, User> usernames = UsernamesStorage.getInstance().getUsernames();
        if (usernames.get(username) == null) {
            User user = new User(name, username, password);
            usersStorage.newUser(user);
        } else {
            System.out.println("Username already exists");
        }

        resp.sendRedirect("/");
    }

}