package by.tms.instaclone.servlet;

import by.tms.instaclone.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;
import java.util.Collection;

import static by.tms.instaclone.storage.KeeperConstants.*;

@WebServlet(value = USER_NEW_POST_PATH)
@MultipartConfig
public class NewPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(NEW_POST_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User curUser = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
        System.out.println(curUser);
        String textPost = req.getParameter("textPost");
        Collection<Part> photosMultiple = req.getParts();
        for (Part photo : photosMultiple) {
            if (photo.getName().equals("photosMultiple")) {
                System.out.println(photo.getName());
            }
        }
        System.out.println(textPost);
        System.out.println(photosMultiple);
//        byte[] avatarBytes = avatar.getInputStream().readAllBytes();


        resp.sendRedirect("/");
    }
}
