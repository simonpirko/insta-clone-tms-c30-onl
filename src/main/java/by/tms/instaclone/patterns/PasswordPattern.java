package by.tms.instaclone.patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordPattern {

    public static synchronized boolean inspect(String password) {
        String passwordPattern = "^(?=.[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,40}$";
        Pattern compile = Pattern.compile(passwordPattern);
        Matcher matcher = compile.matcher(password);
        return matcher.matches();
    }

}