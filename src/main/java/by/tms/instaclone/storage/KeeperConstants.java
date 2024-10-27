package by.tms.instaclone.storage;

import java.util.regex.Pattern;

public class KeeperConstants {
    public static final String CURRENT_USER_ATTRIBUTE = "currentUser";
    public static final String CURRENT_PAGE = "lastPage";
    public static final String CURRENT_SEARCH = "lastSearch";
    public static final String CURRENT_USER_AVATAR_ATTRIBUTE = "currentUserAvatar";

    public static final String LF = "\n";
    public static final String SEPARATOR_CSV = ";";
//    public static final String ID_SEPARATOR = ";";
    public static final String SLAGE = "/";
    public static final String LOGGER_MESSAGE_TEMPLATE = "%s -- %s" + LF;
    public static final String ERROR_TEMPLATE = "ERROR: %s";
    public static final String ERROR_IO_FILE_TEMPLATE = "there was a problem when reading the '%s' file";
    public static final String USERS_CSV_FILE = "csv/users.csv";
    public static final String COMMENTS_CSV_FILE = "csv/comments.csv";
    public static final String REACTIONS_CSV_FILE = "csv/reactions.csv";
    public static final String PHOTOS_CSV_FILE = "csv/photos.csv";
    public static final String PATH_TO_PHOTOS = KeeperConstants.class.getProtectionDomain().getCodeSource().getLocation().getPath().concat("photos/");
    public static final String POSTS_CSV_FILE = "csv/posts.csv";
    public static final String SUBSCRIPTIONS_CSV_FILE = "csv/subscriptions.csv";
    public static final String LOGS_FILE = "logs/logs.txt";

    public static final String USERS_CSV_FORMAT_TEMPLATE = "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + LF;
    public static final String POSTS_CSV_FORMAT_TEMPLATE = "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + LF;
    public static final String PHOTO_CSV_FORMAT_TEMPLATE = "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + LF;
    public static final String SUBSCRIPTIONS_CSV_FORMAT_TEMPLATE = "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + LF;
    public static final String COMMENTS_CSV_FORMAT_TEMPLATE = "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + LF;
    public static final String REACTIONS_CSV_FORMAT_TEMPLATE = "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + "%s" + SEPARATOR_CSV + LF;
    public static final String DATE_TIME_LOGGER_TEMPLATE = "dd/MM/yyyy HH:mm:ss";
    public static final String DATE_TIME_CREATE_POST_TEMPLATE = "dd/MM/yyyy, HH:mm:ss";

    public static final String HOME_JSP = "/pages/home.jsp";
    public static final String HOME_USER_JSP = "/pages/user_home.jsp";
    public static final String REGISTRATION_JSP = "/pages/registration.jsp";
    public static final String PROFILE_JSP = "/pages/profile.jsp";
    public static final String LOGIN_JSP = "/pages/login.jsp";
    public static final String NEW_POST_JSP = "/pages/newPost.jsp";
    public static final String LIST_PEOPLES_JSP = "/pages/list_people.jsp";
    public static final String POST_JSP = "/pages/fullsizepost.jsp";
    public static final String ERROR_JSP = "/pages/error.jsp";

    public static final String REGISTRATION_PATH = "/reg";
    public static final String LOGIN_URL = "/login";
    public static final String USER_HOME_URL = "/user/home";
    public static final String USER_LIKE_URL = "/user/like";
    public static final String USER_DISLIKE_URL = "/user/dislike";
    public static final String USER_NEW_POST_PATH = "/user/newPost";
    public static final String USER_NEW_COMMENT_PATH = "/user/newComment";
    public static final String USER_RECOMMENDATIONS_PATH = "/user/recommendations";
    public static final String USER_SUBSCRIPTION_PATH = "/user/subscription";
    public static final String USER_SUBSCRIBER_PATH = "/user/subscriber";
    public static final String USER_POST_PATH = "/user/post";
    public static final String USER_POST_LIKE_PATH = "/user/post/like";
    public static final String USER_POST_DISLIKE_PATH = "/user/post/dislike";
    public static final String USER_COMMENT_DELETE_PATH = "/user/comment/delete";
    public static final String USER_POST_DELETE_PATH = "/user/post/delete";
    public static final String USER_LOGOUT_PATH = "/user/logout";
    public static final String USER_PROFILE_PATH = "/user/profile/*";
    public static final String USER_PROFILE_URL = "/user/profile";
    public static final String USER_SEARCH_URL = "/user/search";

    public static final boolean LIKE = true;
    public static final boolean DISLIKE = false;

//    public static final String LIKE_BUTTON = "Like";
//    public static final String DISLIKE_BUTTON = "Dislike";
//    public static final String COMMENT_BUTTON = "Comment";

    public static final String LIKE_REACTION = "like";
    public static final String DISLIKE_REACTION = "dislike";
    public static final String NONE_REACTION = "none";

    public static final Pattern NAME_REGEX = Pattern.compile("^[a-zA-Z_]{2,30}$");
    public static final Pattern USERNAME_REGEX = Pattern.compile("^[a-zA-Z0-9]{2,15}$");
    public static final Pattern PASSWORD_REGEX = Pattern.compile("([a-zA-Z0-9!@$%^&*()_\\-+]){8,}");

    // todo сделать присвоение из файла настройки
    public static final boolean IS_PERFORM_LOGGING = true;
    public static final boolean IS_PERFORM_FILE_LOGGING = true;
}
