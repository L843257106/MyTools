package pers.liuhan.toolkit.data;


import org.apache.commons.collections4.CollectionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import pers.liuhan.toolkit.file.FileUtil;
import pers.liuhan.toolkit.file.constant.ResConstant;
import pers.liuhan.toolkit.file.util.XmlUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuhan19691
 */
public class MoveFileScheme {

    private static final String SCHEME_FILE = "MoveFileConfig.xml";

    private String id;

    private String desc;

    private Map<String, String> dirs = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Map<String, String> getDirs() {
        return dirs;
    }

    public void setDirs(Map<String, String> dirs) {
        this.dirs = dirs;
    }

    public static List<MoveFileScheme> loadSchemeFromXml() {
        List<MoveFileScheme> schemeList = new ArrayList<>();
        return schemeList;
    }

    public static void saveSchemeToXml(List<MoveFileScheme> schemeList) {
        if (CollectionUtils.isEmpty(schemeList)) {
            return;
        }
        Document document = DocumentHelper.createDocument();
        Element schemesEle = document.addElement("Schemes");

        for (MoveFileScheme scheme : schemeList) {
            Element schemeEle = schemesEle.addElement("Scheme");
            schemeEle.addAttribute("id", scheme.getId());
            schemeEle.addAttribute("desc", scheme.getDesc());
            for (Map.Entry<String, String> dir : scheme.getDirs().entrySet()) {
                Element dirEle = schemeEle.addElement("Dir");
                dirEle.addAttribute("src", dir.getKey());
                dirEle.addAttribute("tar", dir.getValue());
            }
        }
        try {
            File file = getSchemeFile();
            XmlUtil.writeXml(file, document);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static File getSchemeFile() {
        String path = FileUtil.getCurPath();
        path = FileUtil.concatPath(path, ResConstant.SRC_FILE_NAME);
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        path = FileUtil.concatPath(path, SCHEME_FILE);
        return new File(path);
    }
}
