package pers.liuhan.toolkit.util;


public class StringUtil {

    public static String getLineEnd() {
        return "\n";
    }

    public static String getOneBlank() {
        return " ";
    }

    public static String getBlanks(int n) {
        String blanks = "";
        for (int i = 0; i < n; i++) {
            blanks += " ";
        }
        return blanks;
    }

    public static String getOnePoint() {
        return ".";
    }

}
