package by.tms.instaclone.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tms.instaclone.storage.KeeperConstants.LOGIN_URL;


@WebFilter(urlPatterns = {"/user/*"})
public class SecurityFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (req.getSession().getAttribute("currentUser") == null) {
            res.sendRedirect(LOGIN_URL);
        } else {
            chain.doFilter(req, res);
        }
    }
}