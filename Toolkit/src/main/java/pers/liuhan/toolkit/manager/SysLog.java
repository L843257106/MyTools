package pers.liuhan.toolkit.manager;


import pers.liuhan.toolkit.constant.Punctuation;
import pers.liuhan.toolkit.file.FileUtil;
import pers.liuhan.toolkit.file.constant.ResConstant;
import pers.liuhan.toolkit.util.DateTimeUtil;
import pers.liuhan.toolkit.util.StringUtil;

import javax.swing.*;
import java.io.*;

/**
 * @author liuhan19691
 */
public class SysLog {

    private static String LOGFILENAME = "tolkit.log";

    private static FileWriter logFileWriter;

    private static FileOutputStream fileOutputStream;

    private static OutputStreamWriter logWriter;

    private static JTextArea logScreen;

    private static final int GAP = 3;

    public static void init(JTextArea textArea) {
        logScreen = textArea;
        File logFile = getLogFile();
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            logFileWriter = new FileWriter(logFile);
            fileOutputStream = new FileOutputStream(logFile);
            logWriter = new OutputStreamWriter(fileOutputStream);
        } catch (IOException e) {
            addMsgToScreen("初始化日志失败!");
            return;
        }
        addLog("日志初始化成功.");
    }

    public static synchronized void addLog(String msg) {
        addMsgToScreen(msg);
        try {
            addMsgToLogFile(msg);
        } catch (IOException e) {
            addMsgToScreen("写日志失败!");
        }
    }

    public static synchronized void addLog(String msg, Throwable ex) {
        addLog(msg);
        addLog(ex.getMessage());
    }

    public static synchronized void resetLog() {
        try {
            logFileWriter.write(StringUtil.getNullString());
            logFileWriter.flush();
            logScreen.setText(StringUtil.getNullString());
        } catch (IOException e) {
            addMsgToScreen("写日志失败!");
        }
    }

    public static synchronized void close() {
        try {
            logWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            logFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getLogFile() {
        String path = FileUtil.getCurPath();
        path = FileUtil.concatPath(path, ResConstant.LOG_DIR_NAME);
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        path = FileUtil.concatPath(path, LOGFILENAME);
        return new File(path);
    }

    private static void addMsgToScreen(String msg) {
        logScreen.append(getLogText(msg));
        System.out.println(getLogText(msg));
    }

    private static void addMsgToLogFile(String msg) throws IOException {
        logWriter.write(getLogText(msg));
        logWriter.flush();
    }

    private static String getLogText(String msg) {
        StringBuilder logText = new StringBuilder();
        logText.append(Punctuation.LEFT_SQUARE_BRACKETS);
        logText.append(DateTimeUtil.getCurrentDateTime());
        logText.append(Punctuation.RIGHT_SQUARE_BRACKETS);
        logText.append(Punctuation.COLON_EN).append(StringUtil.getBlanks(GAP));
        logText.append(msg);
        logText.append(Punctuation.LINE_END);
        return logText.toString();
    }
}
