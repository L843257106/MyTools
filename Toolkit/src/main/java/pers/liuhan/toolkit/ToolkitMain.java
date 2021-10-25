package pers.liuhan.toolkit;


import pers.liuhan.toolkit.util.FileUtil;

import java.io.File;

/**
 * @author liuhan19691
 */
public class ToolkitMain {

    public static void main(String[] args) {
        // new MainForm().showForm();
        File src = new File("F:\\TMP\\ParkingLot\\OFI_98_25_20210517.TXT");
        File tar = new File("F:\\TMP\\ParkingLot\\cpdir");
        FileUtil.copyFile(src, tar);
    }
}
