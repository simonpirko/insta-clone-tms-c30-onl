package by.tms.instaclone.servlet;

import by.tms.instaclone.dto.UserDTO;
import by.tms.instaclone.model.User;
import by.tms.instaclone.service.RecommendationsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static by.tms.instaclone.storage.KeeperConstants.*;

@WebServlet(USER_RECOMMENDATIONS_PATH)
public class RecommendationsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User curUser = (User) req.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
        RecommendationsService recommendationsService = new RecommendationsService();
        List<UserDTO> userDTOS = recommendationsService.searchUsers(curUser);
        req.setAttribute("userDTOS", userDTOS);
        getServletContext().getRequestDispatcher(RECOMMENDATIONS_JSP).forward(req, resp);
    }
}
