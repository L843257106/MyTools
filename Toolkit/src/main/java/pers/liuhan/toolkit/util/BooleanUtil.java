package pers.liuhan.toolkit.util;


/**
 * @author liuhan19691
 */
public class BooleanUtil {

    private static final String ZERO_NO = "0";

    public static boolean getBoolean(String zeroOrOne) {
        if (ZERO_NO.equals(zeroOrOne)) {
            return false;
        } else {
            return true;
        }
    }

}
