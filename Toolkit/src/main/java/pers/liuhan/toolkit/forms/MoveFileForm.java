package pers.liuhan.toolkit.forms;

import pers.liuhan.toolkit.component.CbxItem;
import pers.liuhan.toolkit.component.LScrollPane;
import pers.liuhan.toolkit.concurrent.task.MoveFileTask;
import pers.liuhan.toolkit.file.FileUtil;
import pers.liuhan.toolkit.forms.base.BaseForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author liuhan19691
 */
public class MoveFileForm extends BaseForm {

    private JLabel savedLbl;
    private JComboBox<CbxItem> savedCbx;

    private JButton delBtn;
    private JButton saveBtn;

    private JLabel srcLbl;
    private JTextArea srcDirs;
    private LScrollPane srcPane;

    private JButton beginBtn;
    private JLabel statusLbl;

    Map<String, String> schemeMap;

    public MoveFileForm() {
        super();
        schemeMap = new HashMap<>();
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
        addComp(saveBtn);
        commitCurCompToPanel();
    }

    private void initSavedCbx() {
        // todo
    }

    private void paintSrcArea() {
        srcLbl = new JLabel("[请列出源目录和目标目录，之间用'->'分隔，多个目录对应关系分多行列出]");
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
        beginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] text = srcDirs.getText().split("\n");
                String[] line;
                String srcStr;
                String tarStr;
                CountDownLatch downLatch = new CountDownLatch(text.length);
                for (String fileMap : text) {
                    line = fileMap.split("->");
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
                    new Thread(task).start();
                }
                try {
                    statusLbl.setText("[复制文件中......]");
                    downLatch.await();
                    statusLbl.setText("[复制文件完成.]");
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
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

}
