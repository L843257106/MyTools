package pers.liuhan.toolkit.forms;

import pers.liuhan.toolkit.component.CbxItem;
import pers.liuhan.toolkit.component.LScrollPane;
import pers.liuhan.toolkit.forms.base.BaseForm;

import javax.swing.*;

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

    private JLabel tarLbl;
    private JTextArea tarDirs;
    private LScrollPane tarPane;

    private JButton beginBtn;

    public MoveFileForm() {
        super();
        paintSaveInfo();
        paintSaveBtn();
        paintSrcArea();
        paintTarArea();
        paintBegin();

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
        srcLbl = new JLabel("[请列出源目录，多个目录分多行列出]");
        addComp(srcLbl);
        commitCurCompToPanel();
        srcDirs = new JTextArea();
        srcPane = new LScrollPane(srcDirs, LScrollPane.DEFAULT_HEIGHT_UNIT);
        addComp(srcPane);
        commitCurCompToPanel();
    }

    private void paintTarArea() {
        tarLbl = new JLabel("[请列出目标目录，多个目录分多行列出]");
        addComp(tarLbl);
        commitCurCompToPanel();
        tarDirs = new JTextArea();
        tarPane = new LScrollPane(tarDirs, LScrollPane.DEFAULT_HEIGHT_UNIT);
        addComp(tarPane);
        commitCurCompToPanel();
    }

    private void paintBegin() {
        beginBtn = new JButton("[开始移动目录]");
        addComp(beginBtn);
        commitCurCompToPanel();
    }

}
