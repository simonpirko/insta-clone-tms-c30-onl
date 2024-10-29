package by.tms.instaclone.servlet;

import by.tms.instaclone.dto.PublisherCardLastPostDto;
import by.tms.instaclone.dto.UserHomePageDto;
import by.tms.instaclone.model.User;
import by.tms.instaclone.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static by.tms.instaclone.storage.KeeperConstants.*;
import static by.tms.instaclone.storage.KeeperConstants.ERROR_JSP;


@WebServlet(name = "UserHomeServlet", value = USER_HOME_URL)
public class UserHomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserService userService = UserService.getInstance();
        User currentUser = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
        req.getSession().setAttribute(CURRENT_PAGE, USER_HOME_URL);
        Optional<UserHomePageDto> userHomePageContent = userService.collectHomePageContent(currentUser.getUsername());
        if (userHomePageContent.isEmpty()) {
            req.setAttribute("message", "Error! Page not collector!");
            getServletContext().getRequestDispatcher(ERROR_JSP).forward(req, res);
        } else {
            String nameCurrentUser = userHomePageContent.get().getName();
            String usernameCurrentUser = userHomePageContent.get().getUsername();
            List<PublisherCardLastPostDto> publishersCards = userHomePageContent.get().getPublishersCards();
            req.setAttribute("content", publishersCards);
            req.getServletContext().getRequestDispatcher(HOME_USER_JSP).forward(req, res);
        }
    }
}