package pers.liuhan.toolkit.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author liuhan19691
 */
public class DateTimeUtil {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        return formatter.format(Calendar.getInstance().getTime());

    }

}
