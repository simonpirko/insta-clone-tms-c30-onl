package by.tms.instaclone.servlet;

import by.tms.instaclone.DTOs.ProfileDTO;
import by.tms.instaclone.model.User;
import by.tms.instaclone.services.profileService.ProfileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static by.tms.instaclone.storage.KeeperConstants.CURRENT_USER_ATTRIBUTE;
import static by.tms.instaclone.storage.KeeperConstants.USER_PROFILE_PATH;

@WebServlet(USER_PROFILE_PATH)
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usernameProfile = req.getRequestURI().substring(14);
        User user = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);

        ProfileService serviceProfileGuest = new ProfileService(usernameProfile, user);
        Optional<ProfileDTO> profileDTOOptional = serviceProfileGuest.collectorProfile();
        if (profileDTOOptional.isEmpty()) {
            req.setAttribute("message", "Error! Profile not collector!");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        } else {
            ProfileDTO profileDTO = profileDTOOptional.get();
            req.getSession().setAttribute("usernameProfile", profileDTO.getUsername());
            req.getSession().setAttribute("countPostProfile", profileDTO.getCountPost());
            req.getSession().setAttribute("countSubscriberProfile", profileDTO.getCountSubscriber());
            req.getSession().setAttribute("countSubscriptionProfile", profileDTO.getCountSubscription());
            req.getSession().setAttribute("statusSubscriptionProfile", profileDTO.getStatusSubscription());
            getServletContext().getRequestDispatcher("/pages/profile.jsp").forward(req, resp);
        }
    }
}

