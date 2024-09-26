package by.tms.instaclone.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tms.instaclone.storage.KeeperConstants.*;
import static by.tms.instaclone.utilites.SiteLogger.getLogger;

@WebServlet(urlPatterns = LOGIN_PATH)
public class LoginServlet extends HttpServlet {
    private static final String SERVLET_GET_NAME = "doGet() in LoginServlet";
    private static final String SERVLET_POST_NAME = "doPost() in LoginServlet";
    private static final String LOGIN_USER = "username";
    private static final String PASSWORD_USER = "password";
    private static final String PASSWORD_PROBLEM = "Wrong password!";
    private static final String CURRENT_USER_ATTRIBUTE = "currentUser";
    private static final String USER_PROBLEM = "Еhe user's login was not found!";
    private static final String MESSAGE_ATTRIBUTE = "message";
//    private final UserStorage users = new UserStorage();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (IS_PERFORM_LOGGING) getLogger().addRecord(BEGINNING_WORK_MESSAGE_TEMPLATE.formatted(SERVLET_GET_NAME));
        getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
        if (IS_PERFORM_LOGGING) getLogger().addRecord(ENDING_WORK_MESSAGE_TEMPLATE.formatted(SERVLET_GET_NAME));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (IS_PERFORM_LOGGING) getLogger().addRecord(BEGINNING_WORK_MESSAGE_TEMPLATE.formatted(SERVLET_POST_NAME));
        String login = request.getParameter(LOGIN_USER);
        String password = request.getParameter(PASSWORD_USER);
//        ReaderFactory readerFactory = createReaderFactory(USERS);   // todo получаю всех User'ов из файла (или лучше из Storage?), указанного в USERS
//        Reader readerUsers = readerFactory.createReader();
//        User currentUser = null;
//        List<User> users = (List<User>) readerUsers.read();         // в этот List
//        for (User user : users) {                               // перебираю их по username
//            if (user.getUsername().equals(login) && user.getPassword().equals(password)) {
//                currentUser = user;
//                break;
//            }
//        }
//        if (currentUser != null ) {
//            request.getSession().setAttribute(CURRENT_USER_ATTRIBUTE, currentUser);
//            response.sendRedirect(HOME_PATH);         // todo необходимо решить - куда переходить в случае успешного логирования
//        } else {
//            request.setAttribute(MESSAGE_ATTRIBUTE, PASSWORD_PROBLEM);
//            request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
//        }
        if (IS_PERFORM_LOGGING) getLogger().addRecord(ENDING_WORK_MESSAGE_TEMPLATE.formatted(SERVLET_GET_NAME));
    }
}