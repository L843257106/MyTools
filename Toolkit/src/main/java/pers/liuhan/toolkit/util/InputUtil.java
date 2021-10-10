package pers.liuhan.toolkit.util;

import java.util.Scanner;

/**
 * 处理键盘输入工具类
 *
 * @author liuhan19691
 */
public class InputUtil {

    private static Scanner scanner = new Scanner(System.in);

    public static String inputUpperLienString() {
        String line = scanner.nextLine();
        return line.toUpperCase();
    }

    public static String inputLienString() {
        return scanner.nextLine();
    }

    public static int inputInt() {
        return scanner.nextInt();
    }

    public static void closeInput() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
