package by.tms.instaclone.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tms.instaclone.storage.KeeperConstants.LOGIN_URL;
import static by.tms.instaclone.storage.KeeperConstants.USER_LOGOUT_PATH;

@WebServlet(USER_LOGOUT_PATH)
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(LOGIN_URL);
    }
}