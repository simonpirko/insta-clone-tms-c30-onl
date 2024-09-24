package by.tms.instaclone.keepers;

public class KeeperConstants {
    public static final String LF = "\n";
    public static final String SEPARATOR_CSV = ";";
    public static final String SLAGE = "/";
    public static final String BEGINNING_WORK_MESSAGE_TEMPLATE = "'%s' has started working";
    public static final String ENDING_WORK_MESSAGE_TEMPLATE = "'%s' completed the operation";
    public static final String LOGGER_MESSAGE_TEMPLATE = "%s -- %s" + LF;
    public static final String ERROR_TEMPLATE = "ERROR: %s";
    public static final String ERROR_IO_FILE_TEMPLATE = "there was a problem when reading the '%s' file";
    public static final String USERS_CSV_FILE = "src/main/resources/csv/users.csv";
    public static final String POSTS_CSV_FILE = "src/main/resources/csv/posts.csv";
    public static final String LOGS_FILE = "src/main/resources/logs/logs.txt";
    public static final String POSTS = "posts";
    public static final String USERS = "users";

//    public static final String LOGIN_PASSWORD_STORAGE = "C:\\Users\\123\\IdeaProjects\\lesson-26-30-onl-webCalculator\\src\\main\\java\\login_password.csv";
    public static final String USERS_CSV_FORMAT_TEMPLATE = "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + LF;
    public static final String POSTS_CSV_FORMAT_TEMPLATE = "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + LF;
    public static final String DATE_TIME_LOGGER_TEMPLATE = "dd/MM/yyyy HH:mm:ss";
    public static final String HOME_PAGE = "/pages/home.jsp";
    public static final String REGISTRATION_PAGE = "/pages/registration.jsp";
    public static final String LOGIN_JSP = "/pages/login.jsp";
    public static final String PROFILE_PAGE = "/pages/profile.jsp";
    public static final String REGISTRATION_PATH = "/registration";
    public static final String HOME_PATH = SLAGE;
    public static final String LOGIN_PATH = "/login";
    public static final String LOGOUT_PATH = "/logout";
    public static final String PROFILE_PATH = "/profile";

    // todo сделать присвоение из файла настройки
    public static final boolean IS_PERFORM_LOGGING = true;
    public static final boolean IS_PERFORM_FILE_LOGGING = true;
}
