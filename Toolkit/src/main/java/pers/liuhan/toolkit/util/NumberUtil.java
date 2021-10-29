package pers.liuhan.toolkit.util;

/**
 * @author liuhan19691
 */
public class NumberUtil {

    public static final int DEFAULT_ERR_NUM = 0;

    public static int getIntFromString(String strNum, int defaultNum) {
        int result;
        try {
            result = Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            result = defaultNum;
        }
        return result;
    }

    public static int getIntFromString(String strNum) {
        return getIntFromString(strNum, DEFAULT_ERR_NUM);
    }

}
