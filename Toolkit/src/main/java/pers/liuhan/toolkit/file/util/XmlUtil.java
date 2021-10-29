package pers.liuhan.toolkit.file.util;


import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import pers.liuhan.toolkit.manager.SysLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class XmlUtil {

    private static final int INDENT_SIZE = 2;

    public static void writeXml(File file, Document doc) throws FileNotFoundException {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                SysLog.addLog("创建xml失败!");
            }
        }

        OutputFormat format = new OutputFormat();
        // 行缩进
        format.setIndentSize(INDENT_SIZE);
        // 一个结点为一行
        format.setNewlines(true);
        // 去重空格
        format.setTrimText(true);
        format.setPadText(true);
        // 放置xml文件中第二行为空白行
        format.setNewLineAfterDeclaration(false);
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(new FileOutputStream(file), format);
            writer.write(doc);
        } catch (IOException e) {
            SysLog.addLog("保存xml失败!");
        }
    }

}
