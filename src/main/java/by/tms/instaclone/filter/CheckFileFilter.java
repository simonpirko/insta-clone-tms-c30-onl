package by.tms.instaclone.filter;

import by.tms.instaclone.servlet.LoginServlet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebFilter("/*")
public class CheckFileFilter extends HttpFilter {

    @Override
    public void init() {
        ClassLoader classLoader = getClass().getClassLoader();
        List<String> namesFiles = new ArrayList<>();
        namesFiles.add("comments.csv");
        namesFiles.add("photos.csv");
        namesFiles.add("posts.csv");
        namesFiles.add("reactions.csv");
        namesFiles.add("subscriptions.csv");
        namesFiles.add("users.csv");
        File folder = new File((Objects.requireNonNull(classLoader.getResource("/")).getFile()) + "csv");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        for (String name : namesFiles) {
            File csvFile = new File(folder + "/" + name);
            if (!csvFile.exists()) {
                try {
                    csvFile.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }
}
