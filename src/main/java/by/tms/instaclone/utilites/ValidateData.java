package by.tms.instaclone.utilites;

import java.util.regex.Matcher;

import static by.tms.instaclone.keepers.KeeperConstants.*;

public class ValidateData {

    public String name;
    public static final String ERROR_VALIDATE_NAME = "Invalid name: Only letters (min 2 letters, max 15 letters)";
    public static final String ERROR_VALIDATE_USERNAME = "Invalid username: Only letters (min 2 letters, max 15 letters)";
    public static final String ERROR_VALIDATE_PASSWORD = "Invalid password: Letters, numbers, min 8 characters";

    public static boolean validateName (String name) {
        Matcher matcher = NAME_REGEX.matcher(name);
        return matcher.matches();
        }

    public static String getErrorValidateName (String name) {
        if (validateName(name)) return null;
        return ERROR_VALIDATE_NAME;
    }

    public static boolean validateUsername (String username) {
        Matcher matcher = USERNAME_REGEX.matcher(username);
        return matcher.matches();
    }

    public static String getErrorValidateUsername (String username) {
        if (validateName(username)) return null;
        return ERROR_VALIDATE_USERNAME;
    }

    public static boolean validatePassword (String password) {
        Matcher matcher = PASSWORD_REGEX.matcher(password);
        return matcher.matches();
    }

    public static String getErrorValidatePassword (String password) {
        if (validateName(password)) return null;
        return ERROR_VALIDATE_PASSWORD;
    }

}
