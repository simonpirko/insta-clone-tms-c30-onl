package by.tms.instaclone.servlet;

import by.tms.instaclone.dto.PublisherCardLastPostDto;
import by.tms.instaclone.dto.UserHomePageDto;
import by.tms.instaclone.model.User;
import by.tms.instaclone.service.UserHomePageService;

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


@WebServlet(name = "UserHomeServlet", value = USER_HOME_PATH)
public class UserHomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
        UserHomePageService shaperContent = new UserHomePageService(currentUser.getUsername());
        Optional<UserHomePageDto> userHomePageContent = shaperContent.collect();
        if (userHomePageContent.isEmpty()) {
            req.setAttribute("message", "Error! Page not collector!");
            getServletContext().getRequestDispatcher(ERROR_JSP).forward(req, res);
        } else {
            String nameCurrentUser = userHomePageContent.get().getName();
            String usernameCurrentUser = userHomePageContent.get().getUsername();
            List<PublisherCardLastPostDto> publishersCards = userHomePageContent.get().getPublishersCards();
            req.setAttribute("list", publishersCards);
            req.getServletContext().getRequestDispatcher(HOME_USER_JSP).forward(req, res);
        }
    }
}