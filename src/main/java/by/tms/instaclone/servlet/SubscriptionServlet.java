package by.tms.instaclone.servlet;

import by.tms.instaclone.dto.UserDTO;
import by.tms.instaclone.model.User;
import by.tms.instaclone.service.ListPeoplesService;
import by.tms.instaclone.storage.UsersStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static by.tms.instaclone.storage.KeeperConstants.*;

@WebServlet(USER_SUBSCRIPTION_PATH)
public class SubscriptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersStorage usersStorage = UsersStorage.getInstance();
        User curUser = usersStorage.getUser(req.getParameter("curUser"));
        ListPeoplesService listPeoplesService = new ListPeoplesService();
        List<UserDTO> userDTOS = listPeoplesService.searchSubscription(curUser);
        req.setAttribute("userDTOS", userDTOS);
        getServletContext().getRequestDispatcher(LIST_PEOPLES_JSP).forward(req, resp);
    }
}
