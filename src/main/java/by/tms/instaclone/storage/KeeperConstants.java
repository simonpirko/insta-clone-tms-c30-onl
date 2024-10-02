package by.tms.instaclone.storage;

import java.util.regex.Pattern;

public class KeeperConstants {
    public static final String LF = "\n";
    public static final String SEPARATOR_CSV = ";";
    public static final String SLAGE = "/";
    public static final String BEGINNING_WORK_MESSAGE_TEMPLATE = "'%s' has started working";
    public static final String ENDING_WORK_MESSAGE_TEMPLATE = "'%s' completed the operation";
    public static final String LOGGER_MESSAGE_TEMPLATE = "%s -- %s" + LF;
    public static final String ERROR_TEMPLATE = "ERROR: %s";
    public static final String ERROR_IO_FILE_TEMPLATE = "there was a problem when reading the '%s' file";
//    public static final String USERS_CSV_FILE = "C:\\Users\\123\\IdeaProjects\\insta-clone-tms-c30-onl\\src\\main\\resources\\csv\\users.csv";
    public static final String USERS_CSV_FILE = "csv/users.csv";
    public static final String PHOTO_CSV_FILE = KeeperConstants.class.getProtectionDomain().getCodeSource().getLocation().getPath().concat("photos/");
//    public static final String POSTS_CSV_FILE = "C:\\Users\\123\\IdeaProjects\\insta-clone-tms-c30-onl\\src\\main\\resources\\csv\\posts.csv";
    public static final String POSTS_CSV_FILE = "csv/posts.csv";
//    public static final String LOGS_FILE = "C:\\Users\\123\\IdeaProjects\\insta-clone-tms-c30-onl\\src\\main\\resources\\logs\\logs.txt";
    public static final String SUBSCRIPTIONS_CSV_FILE = "csv/subscriptions.csv";
    public static final String LOGS_FILE = "logs/logs.txt";
    public static final String POSTS = "posts";
    public static final String USERS = "users";

    public static final String USERS_CSV_FORMAT_TEMPLATE = "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + LF;
    public static final String POSTS_CSV_FORMAT_TEMPLATE = "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + LF;
    public static final String SUBSCRIPTIONS_CSV_FORMAT_TEMPLATE = "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + LF;
    public static final String DATE_TIME_LOGGER_TEMPLATE = "dd/MM/yyyy HH:mm:ss";
    public static final String HOME_PAGE = "/pages/home.jsp";
    public static final String REGISTRATION_PAGE = "/pages/registration.jsp";
    public static final String PROFILE_PAGE = "/pages/profile.jsp";
    public static final String LOGIN_JSP = "/pages/login.jsp";
    public static final String REGISTRATION_PATH = "/registration";
    public static final String HOME_PATH = SLAGE;
    public static final String LOGIN_PATH = "/user/login";
    public static final String LOGOUT_PATH = "/user/logout";
    public static final String PROFILE_PATH = "/user/profile";
    public static final String LOGIN_USER_PARAMETER = "login";
    public static final String NAME_USER_PARAMETER = "name";
    public static final String PASSWORD_USER_PARAMETER = "password";
    public static final String USER_PARAMETER = "user";
    public static final String CURRENT_USER_ATTRIBUTE = "currentUser";
    public static final String MESSAGE_ATTRIBUTE = "message";
    public static final Pattern NAME_REGEX = Pattern.compile("^[a-zA-Z]{2,15}$");
    public static final Pattern USERNAME_REGEX = Pattern.compile("^[a-zA-Z0-9]{2,15}$");
    public static final Pattern PASSWORD_REGEX = Pattern.compile("([a-zA-Z0-9!@$%^&*()_\\-+]){8,}");

    // todo сделать присвоение из файла настройки
    public static final boolean IS_PERFORM_LOGGING = true;
    public static final boolean IS_PERFORM_FILE_LOGGING = true;
}
