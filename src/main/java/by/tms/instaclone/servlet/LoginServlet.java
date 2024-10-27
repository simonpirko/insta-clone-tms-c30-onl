package by.tms.instaclone.servlet;

import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.PhotoStorage;
import by.tms.instaclone.storage.UsernamesStorage;
import by.tms.instaclone.storage.UsersStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static by.tms.instaclone.storage.KeeperConstants.*;

@WebServlet(name = "LoginServlet", value = LOGIN_URL)
public class LoginServlet extends HttpServlet {
    private static final String LOGIN_USER = "username";
    private static final String PASSWORD_USER = "password";
    private static final String MESSAGE_TRUE = "true";
    private static final String LOGGING_PROBLEM = "isLoggingProblem";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(LOGIN_USER);
        String password = request.getParameter(PASSWORD_USER);
        ConcurrentHashMap<UUID, User> users = UsersStorage.getInstance().getUsers();
        ConcurrentHashMap<String, UUID> usernames = UsernamesStorage.getInstance().getUsernames();
        if (usernames.get(username) != null && users.get(usernames.get(username)).getPassword().equals(password)) {
            Optional<byte []> image = PhotoStorage.getInstance().getByteAvatar(usernames.get(username).toString());
            if (image.isPresent()) {
                String avatar = Base64.getEncoder().encodeToString(image.get());
                request.getSession().setAttribute(CURRENT_USER_AVATAR_ATTRIBUTE, avatar);
            }
            else{
                String avatar = Base64.getEncoder().encodeToString(PhotoStorage.getInstance().getByteAvatar("DefaultAvatar").get());
                request.getSession().setAttribute(CURRENT_USER_AVATAR_ATTRIBUTE, avatar);
            }
            request.getSession().setAttribute(CURRENT_USER_ATTRIBUTE, users.get(usernames.get(username)));
            response.sendRedirect(USER_HOME_URL);
        } else {
            request.setAttribute(LOGGING_PROBLEM, MESSAGE_TRUE);
            request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
        }
    }
}