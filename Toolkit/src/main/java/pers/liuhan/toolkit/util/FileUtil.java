package pers.liuhan.toolkit.util;


import pers.liuhan.toolkit.constant.FileConstant;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @author liuhan19691
 */
public class FileUtil {

    public static void copyFile(String srcName, String tarName) {
        copyFile(new File(srcName), new File(tarName));
    }

    public static void copyFile(File src, File tar) {
        FileChannel srcChannel = null;
        FileChannel tarChannel = null;

        try {
            if (!src.isFile()) {
                return;
            }
            if (tar.isDirectory()) {
                if (!tar.exists()) {
                    tar.mkdir();
                }
                String tarName = tar.getAbsolutePath() + SystemUtil.getFileSeparator() + src.getName();
                tar = new File(tarName);
            }
            if (tar.exists()) {
                tar.delete();
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

    public static boolean createDir(String dir) {
        return createDir(new File(dir));
    }

    public static boolean createDir(File tarFile) {
        return tarFile.mkdir();
    }

    public static boolean deleteFile(String dir) {
        return deleteFile(new File(dir));
    }

    public static boolean deleteFile(File file) {
        if (!file.exists()) {
            return true;
        }
        return file.delete();
    }

    public static String concatPath(String oldPath, String addPath) {
        if (!oldPath.endsWith(FileConstant.FILE_SEPARATOR)) {
            oldPath = oldPath + FileConstant.FILE_SEPARATOR;
        }
        return oldPath + addPath;
    }

    public static String getCurPath() {
        return System.getProperty("user.dir");
    }

    public static String getPath(String fileName) {
        return concatPath(getCurPath(), fileName);
    }

}
