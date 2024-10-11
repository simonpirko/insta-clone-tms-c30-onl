package by.tms.instaclone.utilites;

public class Adapter {
    public static synchronized String adaptPathToOs(String path) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return path.substring(1);
        }
        return path;
    }
}
