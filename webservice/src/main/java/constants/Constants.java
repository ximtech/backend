package constants;

import java.text.SimpleDateFormat;

public class Constants {
    static {
        DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
        TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
    }
    public static final String WIKI_PAGE_URL = "https://en.wikipedia.org/wiki/ISO_4217#Active_codes";
    public static final String SUCCESS = "Success";
    public static final String NO_VALUE = "-";

    public static final SimpleDateFormat DATE_FORMAT;
    public static final SimpleDateFormat TIME_FORMAT;
}
