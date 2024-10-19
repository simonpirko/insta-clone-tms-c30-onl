package by.tms.instaclone.servlet;

import by.tms.instaclone.DTOs.ProfileDTO;
import by.tms.instaclone.model.User;
import by.tms.instaclone.services.profileService.ProfileService;
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
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import static by.tms.instaclone.storage.KeeperConstants.*;

@MultipartConfig
@WebServlet(USER_PROFILE_PATH)
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usernameProfile = req.getRequestURI().substring(14);
        User user = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
PhotoStorage photoStorage = PhotoStorage.getInstance();
Optional <byte[]> avatar= null;
avatar=photoStorage.getByteAvatar(user.getUuid().toString());
if(avatar.isPresent()){
    String avatarka = Base64.getEncoder().encodeToString(avatar.get());
    req.setAttribute("avatar",avatarka);
}
else {
    avatar=photoStorage.getByteAvatar("DefaultAvatar");
    String avatarka = Base64.getEncoder().encodeToString(avatar.get());
    req.setAttribute("avatar",avatarka);
}
        ProfileService serviceProfileGuest = new ProfileService(usernameProfile, user);
        Optional<ProfileDTO> profileDTOOptional = serviceProfileGuest.collectorProfile();
        if (profileDTOOptional.isEmpty()) {
            req.setAttribute("message", "Error! Profile not collector!");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        } else {
            ProfileDTO profileDTO = profileDTOOptional.get();
            req.getSession().setAttribute("usernameProfile", profileDTO.getUsername());
            req.getSession().setAttribute("usernameSession", user.getUsername());
            req.getSession().setAttribute("countPostProfile", profileDTO.getCountPost());
            req.getSession().setAttribute("countSubscriberProfile", profileDTO.getCountSubscriber());
            req.getSession().setAttribute("countSubscriptionProfile", profileDTO.getCountSubscription());
            req.getSession().setAttribute("statusSubscriptionProfile", profileDTO.getStatusSubscription());
            getServletContext().getRequestDispatcher("/pages/profile.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URL = req.getRequestURI().substring(14);
        String[] paramsUrl = URL.split("/");

        switch (paramsUrl[0]) {
            case "subscribe":
                SubscriptionsStorage.getInstance().newSubscription(UsersStorage.getInstance().getUser(paramsUrl[1]), UsersStorage.getInstance().getUser(paramsUrl[2]));
                break;
            case "unsubscribe":
                SubscriptionsStorage.getInstance().deleteSubscriptionFollower(UsersStorage.getInstance().getUser(paramsUrl[1]));
                break;
        }
        Part avatar = req.getPart("avatar");
        User user = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
        PhotoStorage.getInstance().addAvatar(user,avatar);
        resp.sendRedirect("/user/profile/"+"nom1");



    }
}

