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

//        Post post1779 = PostsStorage.getInstance().getPost(UUID.fromString("5bf84e7f-6895-470f-b0a4-785c76c21779"));
//        long countLike1779 = ReactionsStorage.getInstance().getCountLikePost(post1779.getUuid());
//        long countDislike1779 = ReactionsStorage.getInstance().getCountDislikePost(post1779.getUuid());
//
//        Post post3987 = PostsStorage.getInstance().getPost(UUID.fromString("4973448c-0d84-42ca-a76a-c31a1cf83987"));
//        long countLike3987 = ReactionsStorage.getInstance().getCountLikePost(post3987.getUuid());
//        long countDislike3987 = ReactionsStorage.getInstance().getCountDislikePost(post3987.getUuid());

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
