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

@WebServlet(name = "UserHomeEventServlet", value = "/user/home/event")
public class UserHomeEventServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("UserHomeEventServlet");

        User currentUser = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
        Optional<UserHomePageDto> userHomePageContent = new UserService().collectHomePageContent(currentUser.getUsername());
        if (userHomePageContent.isEmpty()) {
            req.setAttribute("message", "Error! Page not collector!");
            getServletContext().getRequestDispatcher(ERROR_JSP).forward(req, res);
        } else {
            String nameCurrentUser = userHomePageContent.get().getName();
            String usernameCurrentUser = userHomePageContent.get().getUsername();
            List<PublisherCardLastPostDto> publishersCards = userHomePageContent.get().getPublishersCards();
            req.setAttribute("content", publishersCards);
//            req.getServletContext().getRequestDispatcher(HOME_USER_JSP).forward(req, res);
            res.sendRedirect(USER_HOME_PATH);
        }

//        res.sendRedirect(USER_HOME_PATH);

    }
}