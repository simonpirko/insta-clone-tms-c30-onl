package by.tms.instaclone;

import by.tms.instaclone.dto.PublisherCardLastPostDto;
import by.tms.instaclone.dto.UserHomePageDto;
import by.tms.instaclone.model.Post;
import by.tms.instaclone.service.UserService;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.ReactionsStorage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserHomePageDtoProbe {

    public static void main(String[] args) {
        String username = "nom1";

        Optional<UserHomePageDto> userHomePageContent = new UserService().collectHomePageContent(username);
        if (userHomePageContent.isEmpty()) {
//            req.setAttribute("message", "Error! Page not collector!");
//            getServletContext().getRequestDispatcher(ERROR_JSP).forward(req, res);
        } else {
            String nameCurrentUser = userHomePageContent.get().getName();
            String usernameCurrentUser = userHomePageContent.get().getUsername();
            List<PublisherCardLastPostDto> publishersCards = userHomePageContent.get().getPublishersCards();
            int i = 0;
//            req.setAttribute("list", publishersCards);
//            req.getServletContext().getRequestDispatcher(HOME_USER_JSP).forward(req, res);
        }
    }
}
