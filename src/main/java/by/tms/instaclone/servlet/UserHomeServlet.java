package by.tms.instaclone.servlet;

import by.tms.instaclone.dto.PublisherCardLastPostDto;
import by.tms.instaclone.dto.UserHomePageDto;
import by.tms.instaclone.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.tms.instaclone.storage.KeeperConstants.*;


@WebServlet(name = "UserHomeServlet", value = USER_HOME_PATH)
public class UserHomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
//        UserHomePageDto userHomePageContent = new UserHomePageDto(currentUser.getUsername()); // todo
//        String nameCurrentUser = userHomePageContent.getName();
//        String usernameCurrentUser = userHomePageContent.getUsername();
//        List<PublisherCardLastPostDto> publishersCardsPages = userHomePageContent.getPublishersCards();
//        req.setAttribute("listExample", publishersCardsPages);
//        req.getServletContext().getRequestDispatcher(HOME_USER_JSP).forward(req, res);
    }
}