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
    public static final String USERS_CSV_FILE = "src/main/resources/users.csv";
    public static final String POSTS_CSV_FILE = "src/main/resources/posts.csv";
    public static final String POSTS = "posts";
    public static final String USERS = "users";

    // todo логирование в файл
    public static final String LOGGING_FILE = "C:\\Users\\123\\IdeaProjects\\lesson28-onl30-jsp\\src\\main\\resources\\logging.txt";
//    public static final String LOGIN_PASSWORD_STORAGE = "C:\\Users\\123\\IdeaProjects\\lesson-26-30-onl-webCalculator\\src\\main\\java\\login_password.csv";
    public static final String USERS_CSV_FORMAT_TEMPLATE = "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + LF;
    public static final String DATE_TIME_LOGGER_TEMPLATE = "dd/MM/yyyy HH:mm:ss";
    public static final String HOME_PAGE = "/pages/home.jsp";
    public static final String REGISTRATION_PAGE = "/pages/registration.jsp";
    public static final String LOGIN_PAGE = "/pages/login.jsp";
    public static final String PROFILE_PAGE = "/pages/profile.jsp";
    public static final String REGISTRATION_PATH = "/user/registration";
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

    // todo сделать присвоение из файла настройки
    public static final boolean IS_PERFORM_LOGGING = true;
    public static final boolean IS_PERFORM_FILE_LOGGING = true;
}
