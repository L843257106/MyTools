package pers.liuhan.toolkit.forms;

import org.apache.commons.lang3.StringUtils;
import pers.liuhan.toolkit.component.CbxItem;
import pers.liuhan.toolkit.component.ComponentUtil;
import pers.liuhan.toolkit.component.InputTextForm;
import pers.liuhan.toolkit.component.LScrollPane;
import pers.liuhan.toolkit.concurrent.factory.ThreadPoolFactory;
import pers.liuhan.toolkit.concurrent.task.MoveFileTask;
import pers.liuhan.toolkit.data.MoveFileScheme;
import pers.liuhan.toolkit.file.FileUtil;
import pers.liuhan.toolkit.forms.base.BaseForm;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author liuhan19691
 */
public class MoveFileForm extends BaseForm {

    private static final String SIC_TAR_SPLIT = "->";

    public static final String SCHEME_KEY = "saveScheme";

    public static final String TEXT_AREA_KEY = "textAreaKey";

    private JLabel savedLbl;
    private JComboBox<CbxItem> savedCbx;

    private JButton delBtn;
    private JButton saveBtn;

    private JLabel srcLbl;
    private JTextArea srcDirs;
    private LScrollPane srcPane;

    private JButton beginBtn;
    private JLabel statusLbl;

    private List<MoveFileScheme> schemeList;

    public MoveFileForm() {
        super();
        addFormEvent();
        schemeList = new ArrayList<>();
        paintSaveInfo();
        paintSaveBtn();
        paintSrcArea();
        paintBegin();
        paintStatusLabel();

        paintForm();
    }

    private void paintSaveInfo() {
        savedLbl = new JLabel("保存的方案:");
        addComp(savedLbl);
        savedCbx = new JComboBox();
        addComp(savedCbx);
        initSavedCbx();
        commitCurCompToPanel();
    }

    private void paintSaveBtn() {
        delBtn = new JButton("[删除当前方案]");
        addComp(delBtn);
        saveBtn = new JButton("[保存当前方案]");
        saveBtn.addActionListener(e -> {
            new InputTextForm(textArea -> {
                String[] lines = ComponentUtil.getTextAreaContents(textArea);
                for (String line : lines) {
                    if (StringUtils.isBlank(line)) {
                        continue;
                    }
                    // 输入多个时，取第一个
                    savedCbx.addItem(new CbxItem(line));
                    break;
                }
            }).showModel();
        });
        addComp(saveBtn);
        commitCurCompToPanel();
    }

    private void initSavedCbx() {
        // todo
    }

    private void paintSrcArea() {
        srcLbl = new JLabel("[请列出源目录和目标目录，之间用" + SIC_TAR_SPLIT + "分隔，多个目录对应关系分多行列出]");
        addComp(srcLbl);
        commitCurCompToPanel();
        srcDirs = new JTextArea();
        srcPane = new LScrollPane(srcDirs, LScrollPane.DEFAULT_HEIGHT_UNIT);
        srcPane.setHeightUnit(12);
        addComp(srcPane);
        commitCurCompToPanel();
    }

    private void paintBegin() {
        beginBtn = new JButton("[开始移动目录]");
        beginBtn.addActionListener(e -> {
            String[] text = ComponentUtil.getTextAreaContents(srcDirs);
            String[] line;
            String srcStr;
            String tarStr;
            CountDownLatch downLatch = new CountDownLatch(text.length);
            for (String fileMap : text) {
                line = fileMap.split(SIC_TAR_SPLIT);
                if (line.length != 2) {
                    downLatch.countDown();
                    continue;
                }
                srcStr = line[0];
                tarStr = line[1];
                FileUtil.clearDir(tarStr);
                FileUtil.createDir(tarStr);
                MoveFileTask task = new MoveFileTask(srcStr, tarStr);
                task.setDownLatch(downLatch);

                ExecutorService executorService = ThreadPoolFactory.getFixedThreadPool();
                executorService.execute(task);
            }
            try {
                statusLbl.setText("[复制文件中......]");
                downLatch.await();
                statusLbl.setText("[复制文件完成.]");
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        addComp(beginBtn);
        commitCurCompToPanel();
    }

    private void paintStatusLabel() {
        statusLbl = new JLabel("[等待输入文件路径...]");
        addComp(statusLbl);
        commitCurCompToPanel();
    }

    private void addFormEvent() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                List<MoveFileScheme> schemeList = new ArrayList<>();
                schemeList.add(new MoveFileScheme());
                MoveFileScheme.saveSchemeToXml(schemeList);
            }
        });
    }

}
