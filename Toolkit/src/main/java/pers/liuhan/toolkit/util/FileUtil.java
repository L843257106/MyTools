package pers.liuhan.toolkit.util;


import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @author liuhan19691
 */
public class FileUtil {

    public static void copyFile(File src, File tar) {
        FileChannel srcChannel = null;
        FileChannel tarChannel = null;

        try {
            if (src.isDirectory()) {
                return;
            }
            if (tar.isDirectory()) {
                if (!tar.exists()) {
                    tar.mkdir();
                }
                String tarName = tar.getAbsolutePath() + SystemUtil.getFileSeparator() + src.getName();
                tar = new File(tarName);
            }

            srcChannel = new FileInputStream(src).getChannel();
            tarChannel = new FileOutputStream(tar).getChannel();

            tarChannel.transferFrom(srcChannel, 0, srcChannel.size());

        } catch (FileNotFoundException e) {
            System.out.println("File not fount.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                srcChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                tarChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createDir(String dir) {
        File dirFile = new File(dir);
        if (dirFile.exists()) {
            if (!dirFile.isDirectory()) {
                dirFile.mkdir();
            }
            return;
        }
        dirFile.mkdir();
    }

}
