package by.tms.instaclone.servlet;

import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.PhotoStorage;
import by.tms.instaclone.storage.SubscriptionsStorage;
import by.tms.instaclone.storage.UsersStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

import static by.tms.instaclone.storage.KeeperConstants.CURRENT_USER_ATTRIBUTE;
@WebServlet("/user/profileService/*")
public class ProfileServiceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URL = req.getRequestURI().substring(21);
        String[] paramsUrl = URL.split("/");
        switch (paramsUrl[0]) {
            case "subscribe":
                SubscriptionsStorage.getInstance().newSubscription(UsersStorage.getInstance().getUser(paramsUrl[1]), UsersStorage.getInstance().getUser(paramsUrl[2]));
                resp.sendRedirect("/user/profile/" + paramsUrl[2]);
                break;
            case "unsubscribe":
                SubscriptionsStorage.getInstance().unsubscribe(UsersStorage.getInstance().getUser(paramsUrl[1]).getUuid(), UsersStorage.getInstance().getUser(paramsUrl[2]).getUuid());
                resp.sendRedirect("/user/profile/" + paramsUrl[2]);
                break;
        }
    }
}
