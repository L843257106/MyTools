package pers.liuhan.toolkit.file;


import pers.liuhan.toolkit.constant.FileConstant;
import pers.liuhan.toolkit.file.field.FileFieldInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuhan19691
 */
public class FileReader {

    public List<Map<String, String>> readFileByFieldInfo(List<FileFieldInfo> fieldInfos, String fileName) {
        List<Map<String, String>> fileData = new ArrayList<>();

        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        InputStream inputStream = null;
        InputStreamReader streamReader = null;
        BufferedReader bufferedReader = null;
        String lineStr;
        String fieldStr;
        Map<String, String> lineMap;
        byte[] lineBytes;
        byte[] fieldBytes;
        ByteArrayInputStream bisLine = null;
        try {
            inputStream = new FileInputStream(file);
            streamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(streamReader);

            while ((lineStr = bufferedReader.readLine()) != null) {
                lineBytes = lineStr.getBytes(FileConstant.DEFAULT_ENCODE);
                bisLine = new ByteArrayInputStream(lineBytes);
                lineMap = new HashMap<>();
                for (FileFieldInfo fieldInfo : fieldInfos) {
                    fieldBytes = new byte[fieldInfo.getFieldLength()];
                    bisLine.read(fieldBytes, 0, fieldBytes.length);
                    fieldStr = new String(fieldBytes, FileConstant.DEFAULT_ENCODE);
                    lineMap.put(fieldInfo.getFieldName(), fieldStr);
                }
                fileData.add(lineMap);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                streamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileData;
    }

}
