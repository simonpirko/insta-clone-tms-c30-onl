package by.tms.instaclone.servlet;

import by.tms.instaclone.dto.PublisherCardDto;
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
        UserHomePageDto pageDto = new UserHomePageDto(currentUser.getUsername());
        String nameCurrentUser = pageDto.getName();
        String usernameCurrentUser = pageDto.getUsername();
        List<PublisherCardDto> publishersCardsPages = pageDto.getPublishersCards();

        int publishersCardsCount = publishersCardsPages.size();

//        req.setAttribute("nameCurrentUser", currentUser.getName());
//        HashMap<UUID, User> followers = SubscriptionsStorage.getInstance().getFollowersPublisher(currentUser);
//        HashMap<UUID, User> publishers = SubscriptionsStorage.getInstance().getPublishersFollower(currentUser);
//        HashMap<User, Post> hotPostsPublishers = new HashMap<>();
//        Post hotPost = null;
//        for (Map.Entry entry: publishers.entrySet()) {
//            User owner = (User) entry.getValue();
//            hotPost = PostsStorage.getInstance().getHotPostOwner(owner);
//            hotPostsPublishers.put(owner,hotPost);
//        }
//        req.setAttribute("textPostCurrentUser", hotPost.getText());
//        req.setAttribute("isName1", "true");
//        req.setAttribute("carousel", "carousel" + "1");
//        req.setAttribute("count", "3");

        req.getServletContext().getRequestDispatcher(HOME_USER_JSP).forward(req, res);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        String name = req.getParameter("name");
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");

//        User currentUser = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
//        req.setAttribute("nameCurrentUser",currentUser.getName());
//
//        int i = 0;

//        if (ValidateData.validateName(name) && ValidateData.validateUsername(username) && ValidateData.validatePassword(password)) {
//            ConcurrentHashMap<String, UUID> usernames = UsernamesStorage.getInstance().getUsernames();
//            if(usernames.get(username)==null){
//                usersStorage.newUser(name,username,password);
//                resp.sendRedirect(USER_HOME_PATH);
//            } else {
//                req.setAttribute("message","Username already exists");
//                req.getRequestDispatcher(REGISTRATION_JSP).forward(req,resp);
//            }
//        } else {
//            String errorName = ValidateData.getErrorValidateName(name);
//            String errorUsername = ValidateData.getErrorValidateUsername(username);
//            String errorPassword = ValidateData.getErrorValidatePassword(password);
//            req.setAttribute("errorName", errorName);
//            req.setAttribute("errorUsername", errorUsername);
//            req.setAttribute("errorPassword", errorPassword);
//            req.getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
//        }
//    }

}