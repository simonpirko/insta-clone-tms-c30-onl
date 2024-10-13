package by.tms.instaclone.utilites;

public class Adapter {
    private String path;

    public Adapter(String path) {
        this.path = path;
    }

    public String getPathToOs() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return path.substring(1);
        }
        return path;
    }
}
