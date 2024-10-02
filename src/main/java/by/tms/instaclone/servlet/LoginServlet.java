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
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static by.tms.instaclone.storage.KeeperConstants.*;
import static by.tms.instaclone.utilites.SiteLogger.getLogger;

@WebServlet(name = "LoginServlet", value = LOGIN_PATH)
public class LoginServlet extends HttpServlet {
    private static final String SERVLET_GET_NAME = "doGet() in LoginServlet";
    private static final String SERVLET_POST_NAME = "doPost() in LoginServlet";
    private static final String LOGIN_USER = "username";
    private static final String PASSWORD_USER = "password";
    private static final String CURRENT_USER_ATTRIBUTE = "currentUser";
    private static final String MESSAGE_TRUE = "true";
    private static final String USERNAME_MISSING = "isUsernameMissing";
    private static final String USERNAME_PROBLEM = "isUsernameProblem";
    private static final String PASSWORD_MISSING = "isPasswordMissing";
    private static final String PASSWORD_PROBLEM = "isPasswordProblem";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (IS_PERFORM_LOGGING) getLogger().addRecord(BEGINNING_WORK_MESSAGE_TEMPLATE.formatted(SERVLET_GET_NAME));
        getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
        if (IS_PERFORM_LOGGING) getLogger().addRecord(ENDING_WORK_MESSAGE_TEMPLATE.formatted(SERVLET_GET_NAME));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (IS_PERFORM_LOGGING) getLogger().addRecord(BEGINNING_WORK_MESSAGE_TEMPLATE.formatted(SERVLET_POST_NAME));
        String username = request.getParameter(LOGIN_USER);
        String password = request.getParameter(PASSWORD_USER);
        ConcurrentHashMap<UUID, User> users = UsersStorage.getInstance().getUsers();
        ConcurrentHashMap<String, UUID> usernames = UsernamesStorage.getInstance().getUsernames();
        if (username.isEmpty()) {
            request.setAttribute(USERNAME_MISSING, MESSAGE_TRUE);
            request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
        }
        if (password.isEmpty()) {
            request.setAttribute(PASSWORD_MISSING, MESSAGE_TRUE);
            request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
        }
        if (username.length() > 0 && usernames.get(username) == null) {
            request.setAttribute(USERNAME_PROBLEM, MESSAGE_TRUE);
            request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
        }
        if (usernames.get(username) != null && users.get(usernames.get(username)).getPassword().equals(password)) {
            request.getSession().setAttribute(CURRENT_USER_ATTRIBUTE, users.get(usernames.get(username)));
            response.sendRedirect(HOME_PATH);
        } else {
            request.setAttribute(PASSWORD_PROBLEM, MESSAGE_TRUE);
            request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
        }
        if (IS_PERFORM_LOGGING) getLogger().addRecord(ENDING_WORK_MESSAGE_TEMPLATE.formatted(SERVLET_GET_NAME));
    }
}