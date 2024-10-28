package by.tms.instaclone.servlet;

import by.tms.instaclone.service.SearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.tms.instaclone.storage.KeeperConstants.*;

@WebServlet(USER_SEARCH_URL)
public class SearchServlet extends HttpServlet {

    SearchService searchService = new SearchService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().setAttribute(CURRENT_PAGE, USER_SEARCH_URL);
        req.getServletContext().getRequestDispatcher("/pages/search.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String request;
        if (req.getSession().getAttribute(CURRENT_PAGE) == "RELOAD_LAST_SEARCH") {
            request = req.getSession().getAttribute(CURRENT_SEARCH).toString();
        }
        else {
            request = req.getParameter("keyword");
            req.getSession().setAttribute(CURRENT_SEARCH, request);

        }
        List<?> result;
        request = req.getParameter("keyword");
        req.getSession().setAttribute("type", req.getParameter("btnradio"));
        switch (req.getSession().getAttribute("type").toString()) {
            case "users":
                result = searchService.searchUsers(request);
                req.getSession().setAttribute("result", result);
                break;
            case "posts":
                result = searchService.searchPosts(request);
                req.getSession().setAttribute("result", result);
                break;
        }

        resp.sendRedirect(USER_SEARCH_URL);
    }
}
