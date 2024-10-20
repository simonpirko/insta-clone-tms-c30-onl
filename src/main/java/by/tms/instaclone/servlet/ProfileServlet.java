package by.tms.instaclone.servlet;

import by.tms.instaclone.dto.ProfileDTO;
import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.SubscriptionsStorage;
import by.tms.instaclone.storage.UsersStorage;
import by.tms.instaclone.service.ProfileService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import static by.tms.instaclone.storage.KeeperConstants.*;


@WebServlet(USER_PROFILE_PATH)
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usernameProfile = req.getRequestURI().substring(14);
        User user = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
        ProfileService serviceProfile = new ProfileService();
        Optional<ProfileDTO> profileDTOOptional = serviceProfile.collectorProfile(usernameProfile, user);
        if (profileDTOOptional.isEmpty()) {
            req.setAttribute("message", "Error! Profile not collector!");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        } else {
            ProfileDTO profileDTO = profileDTOOptional.get();
            req.setAttribute("profileDTO", profileDTO);
            req.setAttribute("avatar", profileDTO.getAvatar());
            req.getSession().setAttribute("usernameProfile", profileDTO.getUsername());
            req.getSession().setAttribute("usernameSession", user.getUsername());
            req.getSession().setAttribute("countPostProfile", profileDTO.getCountPost());
            req.getSession().setAttribute("countSubscriberProfile", profileDTO.getCountSubscriber());
            req.getSession().setAttribute("countSubscriptionProfile", profileDTO.getCountSubscription());
            req.getSession().setAttribute("statusSubscriptionProfile", profileDTO.getStatusSubscription());
            getServletContext().getRequestDispatcher("/pages/profile.jsp").forward(req, resp);
        }
    }

}

