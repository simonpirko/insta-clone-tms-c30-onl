package by.tms.instaclone.servlet;

import by.tms.instaclone.utilites.SiteLogger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tms.instaclone.keepers.KeeperConstants.*;
import static by.tms.instaclone.utilites.SiteLogger.getLogger;

@WebServlet(urlPatterns = LOGIN_PATH)
public class LoginServlet extends HttpServlet {
    private static final String SERVLET_GET_NAME = "doGet() in LoginServlet";
    private static final String SERVLET_POST_NAME = "doPost() in LoginServlet";
    private static final String LOGIN_USER = "login";
    private static final String PASSWORD_USER = "password";
    private static final String PASSWORD_PROBLEM = "Wrong password!";
    private static final String USER_PROBLEM = "Еhe user's login was not found!";
//    private final UserStorage users = new UserStorage();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (IS_PERFORM_LOGGING) getLogger().addRecord(BEGINNING_WORK_MESSAGE_TEMPLATE.formatted(SERVLET_GET_NAME));

        getServletContext().getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
        if (IS_PERFORM_LOGGING) getLogger().addRecord(ENDING_WORK_MESSAGE_TEMPLATE.formatted(SERVLET_GET_NAME));
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (IS_PERFORM_LOGGING) logIn(BEGINNING_WORK_MESSAGE_TEMPLATE.formatted(SERVLET_POST_NAME));
//        String login = req.getParameter(LOGIN_USER);
//        String password = req.getParameter(PASSWORD_USER);
//
//        Optional<User> byUserName = users.findByLogin(login);
//        if (byUserName.isPresent()) {
//            User user = byUserName.get();
//            if (user.getPassword().equals(password)) {
//                req.getSession().setAttribute(CURRENT_USER_ATTRIBUTE, user);
//                resp.sendRedirect(HOME_PATH);
//            } else {
//                req.setAttribute(MESSAGE_ATTRIBUTE, PASSWORD_PROBLEM);
//                req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
//            }
//        } else {
//            req.setAttribute(MESSAGE_ATTRIBUTE, USER_PROBLEM);
//            req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
//        }
//        if (IS_PERFORM_LOGGING) logIn(ENDING_WORK_MESSAGE_TEMPLATE.formatted(SERVLET_GET_NAME));
//    }
}