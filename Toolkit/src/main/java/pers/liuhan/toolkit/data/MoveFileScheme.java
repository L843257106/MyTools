package pers.liuhan.toolkit.data;


import org.apache.commons.collections4.MapUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import pers.liuhan.toolkit.file.FileUtil;
import pers.liuhan.toolkit.file.constant.ResConstant;
import pers.liuhan.toolkit.file.util.XmlUtil;
import pers.liuhan.toolkit.manager.SysLog;

import java.io.File;
import java.io.FileNotFoundException;
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

    public static Map<String, MoveFileScheme> loadSchemeFromXml() {
        Map<String, MoveFileScheme> schemeMap = new HashMap<>();
        SAXReader saxReader = new SAXReader();
        File file = getSchemeFile();
        if (!file.exists()) {
            return schemeMap;
        }
        try {
            Document document = saxReader.read(file);
            Element eleSchemes = document.getRootElement();
            List<Element> eleSchemeList = eleSchemes.elements();
            for (Element eleScheme : eleSchemeList) {
                MoveFileScheme fileScheme = new MoveFileScheme();
                fileScheme.setId(eleScheme.attributeValue("id"));
                fileScheme.setDesc(eleScheme.attributeValue("desc"));
                List<Element> eleDirLis = eleScheme.elements();
                for (Element eleDir : eleDirLis) {
                    fileScheme.getDirs().put(eleDir.attributeValue("src"), eleDir.attributeValue("tar"));
                }
                schemeMap.put(fileScheme.getId(), fileScheme);
            }
        } catch (DocumentException e) {
            SysLog.addLog("加载文件复制方案出错!", e);
        }
        return schemeMap;
    }

    public static void saveSchemeToXml(Map<String, MoveFileScheme> schemeMap) {
        if (MapUtils.isEmpty(schemeMap)) {
            return;
        }
        Document document = DocumentHelper.createDocument();
        Element schemesEle = document.addElement("Schemes");
        MoveFileScheme scheme;
        for (Map.Entry<String, MoveFileScheme> node : schemeMap.entrySet()) {
            scheme = node.getValue();
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
            SysLog.addLog("保存文件复制方案出错!", e);
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
