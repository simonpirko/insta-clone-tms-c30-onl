
package by.tms.instaclone.servlet;

import by.tms.instaclone.model.User;
import by.tms.instaclone.storage.PhotoStorage;
import by.tms.instaclone.storage.UsernamesStorage;
import by.tms.instaclone.storage.UsersStorage;

import by.tms.instaclone.utilites.ValidateData;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static by.tms.instaclone.storage.KeeperConstants.*;
@MultipartConfig
@WebServlet(name = "RegistrationServlet", value = REGISTRATION_PATH)
public class RegistrationServlet extends HttpServlet {

    private final UsersStorage usersStorage = UsersStorage.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        getServletContext().getRequestDispatcher("/pages/reg.jsp").forward(req, resp);
        getServletContext().getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (ValidateData.validateName(name) && ValidateData.validateUsername(username) && ValidateData.validatePassword(password)) {
            ConcurrentHashMap<String, UUID> usernames = UsernamesStorage.getInstance().getUsernames();
            if(usernames.get(username)==null){
               User user = usersStorage.newUser(name,username,password);
                Part avatar = req.getPart("avatar");
                PhotoStorage.getInstance().addAvatar(user, avatar);
                resp.sendRedirect(LOGIN_URL);
            } else {
                req.setAttribute("message","Username already exists");
                req.getRequestDispatcher(REGISTRATION_JSP).forward(req,resp);
            }
        } else {
            String errorName = ValidateData.getErrorValidateName(name);
            String errorUsername = ValidateData.getErrorValidateUsername(username);
            String errorPassword = ValidateData.getErrorValidatePassword(password);
            req.setAttribute("errorName", errorName);
            req.setAttribute("errorUsername", errorUsername);
            req.setAttribute("errorPassword", errorPassword);
            req.getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
        }
    }
}
