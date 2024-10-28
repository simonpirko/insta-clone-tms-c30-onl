package by.tms.instaclone.servlet;


import by.tms.instaclone.dto.RefactorProfileDTO;
import by.tms.instaclone.model.User;
import by.tms.instaclone.service.RefactorProfileService;
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

import static by.tms.instaclone.storage.KeeperConstants.CURRENT_USER_ATTRIBUTE;
import static by.tms.instaclone.storage.KeeperConstants.CURRENT_USER_AVATAR_ATTRIBUTE;

@MultipartConfig
@WebServlet("/user/refactorProfile/*")
public class RefactorProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
        RefactorProfileService refactorProfileService = new RefactorProfileService();
        Optional<RefactorProfileDTO> refactorProfileDTOOptional = refactorProfileService.collectDataProfile(user.getUsername());
        if (refactorProfileDTOOptional.isEmpty()) {
            req.setAttribute("message", "Error! Profile not collector!");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        } else {
            RefactorProfileDTO refactorProfileDTO = refactorProfileDTOOptional.get();
            req.setAttribute("DataProfile", refactorProfileDTO);
            getServletContext().getRequestDispatcher("/pages/refactor_profile.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URL = req.getRequestURI().substring(22);
        String[] paramsUrl = URL.split("/");
        User user = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
        System.out.println("URL: " + paramsUrl[0]);
        switch (paramsUrl[0]) {
            case "setAvatar":
                Part avatar = req.getPart("avatar");
                PhotoStorage.getInstance().addAvatar(user, avatar);
                Optional<byte []> image = PhotoStorage.getInstance().getByteAvatar(user.getUuid().toString());
                String newAvatar = Base64.getEncoder().encodeToString(image.get());
                req.getSession().setAttribute(CURRENT_USER_AVATAR_ATTRIBUTE, newAvatar);
                resp.sendRedirect("/user/refactorProfile/" + user.getUsername());
                break;
            case "setName":
                resp.sendRedirect("/user/refactorProfile/" + user.getUsername());
                break;
            case "setUsername":
                resp.sendRedirect("/user/refactorProfile/"+ user.getUsername());
                break;
            default:  resp.sendRedirect("/user/refactorProfile/" + user.getUsername());
        }


    }


}

