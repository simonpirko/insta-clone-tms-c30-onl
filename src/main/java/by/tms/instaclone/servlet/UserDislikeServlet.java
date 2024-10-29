package by.tms.instaclone.servlet;

import by.tms.instaclone.dto.PostDto;
import by.tms.instaclone.dto.PublisherCardLastPostDto;
import by.tms.instaclone.dto.UserHomePageDto;
import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.User;
import by.tms.instaclone.service.SearchService;
import by.tms.instaclone.service.UserService;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.ReactionsStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static by.tms.instaclone.storage.KeeperConstants.*;

@WebServlet(name = "UserDislikeServlet", value = USER_DISLIKE_URL)
public class UserDislikeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserService userService = UserService.getInstance();
        User currentUser = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
        String uuidPost = req.getParameter("uuidPost");
        Post post = PostsStorage.getInstance().getPost(UUID.fromString(uuidPost));
        ReactionsStorage.getInstance().newReaction(post, currentUser, DISLIKE);
        if (req.getSession().getAttribute(CURRENT_PAGE) == USER_HOME_URL) {
            Optional<UserHomePageDto> userHomePageContent = userService.collectHomePageContent(currentUser.getUsername());
            if (userHomePageContent.isEmpty()) {
                req.setAttribute("message", "Error! Page not collector!");
                getServletContext().getRequestDispatcher(ERROR_JSP).forward(req, res);
            } else {
                List<PublisherCardLastPostDto> publishersCards = userHomePageContent.get().getPublishersCards();
                req.setAttribute("content", publishersCards);
                res.sendRedirect(USER_HOME_URL);
            }
        } else if (req.getSession().getAttribute(CURRENT_PAGE) == USER_SEARCH_URL) {
            req.getSession().setAttribute(CURRENT_PAGE, USER_DISLIKE_URL);
            List<PostDto> result = (List<PostDto>) req.getSession().getAttribute("result");
            List<PostDto> updateResult = SearchService.getInstance().updateDTO(result);
            req.getSession().setAttribute("result", updateResult);
            res.sendRedirect(USER_SEARCH_URL);
        }
    }
}