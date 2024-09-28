package by.tms.instaclone.servlet;

import by.tms.instaclone.keepers.servants.UsersStorage;
import by.tms.instaclone.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UsersStorage usersStorage= UsersStorage.getInstance();
        ConcurrentHashMap<String,String> usernames=UsernamesStorage.getInstance().getUsernames();
        if(usernames.get(username)==null){
            User user=new User(name,username,password);
            usersStorage.newUser(user);
        }else{
            req.setAttribute("message","Username already exists");
            req.getRequestDispatcher("reg.jsp");
        }
    }

}