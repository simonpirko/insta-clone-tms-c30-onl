package by.tms.instaclone.servlet;

import by.tms.instaclone.model.Post;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.PostsStorage;
import by.tms.instaclone.storage.SubscriptionsStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static by.tms.instaclone.storage.KeeperConstants.CURRENT_USER_ATTRIBUTE;
import static by.tms.instaclone.storage.KeeperConstants.USER_HOME_PATH;


@WebServlet(name = "UserHomeServlet", value = USER_HOME_PATH)
public class UserHomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        User currentUser = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
        req.setAttribute("nameCurrentUser",currentUser.getName());
        HashMap<UUID, User> followers = SubscriptionsStorage.getInstance().getFollowersPublisher(currentUser);
        HashMap<UUID, User> publishers = SubscriptionsStorage.getInstance().getPublishersFollower(currentUser);
        HashMap<User, Post> hotPostsPublishers = new HashMap<>();
        Post hotPost;
        for (Map.Entry entry: publishers.entrySet()) {
            User owner = (User) entry.getValue();
            hotPost = PostsStorage.getInstance().getHotPostOwner(owner);
            hotPostsPublishers.put(owner,hotPost);
        }


        req.getServletContext().getRequestDispatcher("/pages/template.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        String name = req.getParameter("name");
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
        User currentUser = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
        req.setAttribute("nameCurrentUser",currentUser.getName());

        int i = 0;

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
    }

}