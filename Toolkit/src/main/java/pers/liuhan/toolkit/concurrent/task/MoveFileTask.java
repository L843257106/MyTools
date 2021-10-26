package pers.liuhan.toolkit.concurrent.task;


import pers.liuhan.toolkit.file.FileUtil;

import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

/**
 * @author liuhan19691
 */
public class MoveFileTask implements Runnable {

    private String srcStr;
    private String tarStr;
    private CountDownLatch downLatch;

    public MoveFileTask(String srcStr, String tarStr) {
        this.srcStr = srcStr;
        this.tarStr = tarStr;
    }

    public void setDownLatch(CountDownLatch downLatch) {
        this.downLatch = downLatch;
    }

    @Override
    public void run() {
        File srcFile = new File(srcStr);
        if (!srcFile.exists()) {
            downLatch.countDown();
            return;
        }

        File tempFile;
        File[] childFiles;
        File tarFile;
        String tarName;
        Deque<File> files = new LinkedList<>();
        files.offer(srcFile);
        while (!files.isEmpty()) {
            tempFile = files.poll();
            tarName = tempFile.getAbsolutePath().replace(srcStr, tarStr);
            tarFile = new File(tarName);
            if (tempFile.isDirectory()) {
                childFiles = tempFile.listFiles();
                for (File child : childFiles) {
                    files.offer(child);
                }
                tarFile.mkdir();
            } else {
                FileUtil.copyFile(tempFile, tarFile);
            }
        }
        downLatch.countDown();
    }
}
