package pers.liuhan.toolkit.forms;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import pers.liuhan.toolkit.component.CbxItem;
import pers.liuhan.toolkit.component.ComponentUtil;
import pers.liuhan.toolkit.component.InputTextForm;
import pers.liuhan.toolkit.component.LScrollPane;
import pers.liuhan.toolkit.concurrent.factory.ThreadPoolFactory;
import pers.liuhan.toolkit.concurrent.task.MoveFileTask;
import pers.liuhan.toolkit.constant.Punctuation;
import pers.liuhan.toolkit.data.MoveFileScheme;
import pers.liuhan.toolkit.file.FileUtil;
import pers.liuhan.toolkit.forms.base.BaseForm;
import pers.liuhan.toolkit.util.MapUtil;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author liuhan19691
 */
public class MoveFileForm extends BaseForm {

    private JLabel schemeLbl;
    private JComboBox<CbxItem> schemeCbx;

    private JButton mdfBtn;
    private JButton delBtn;
    private JButton saveBtn;

    private JLabel srcLbl;
    private JTextArea srcDirs;
    private LScrollPane srcPane;

    private JButton beginBtn;
    private JLabel statusLbl;

    private Map<String, MoveFileScheme> schemeMap;

    public MoveFileForm() {
        super();
        addFormEvent();
        initSavedCbx();
    }

    @Override
    protected void fillComponents() {
        paintSaveInfo();
        paintSaveBtn();
        paintSrcArea();
        paintBegin();
        paintStatusLabel();
    }

    private void paintSaveInfo() {
        schemeLbl = new JLabel("保存的方案:");
        addComp(schemeLbl);
        schemeCbx = new JComboBox();
        schemeCbx.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                CbxItem item = (CbxItem) e.getItem();
                MoveFileScheme scheme = schemeMap.get(item.getKey());
                if (Objects.isNull(scheme)) {
                    return;
                }
                if (Objects.isNull(srcDirs)) {
                    return;
                }
                ComponentUtil.fillTextAreaByMap(srcDirs, scheme.getDirs());
            }
        });
        addComp(schemeCbx);
        initSavedCbx();
        commitCurCompToPanel();
    }

    private void paintSaveBtn() {
        mdfBtn = new JButton("[更新当前方案]");
        mdfBtn.addActionListener(e -> {
            if (schemeCbx.getItemCount() == 0 || MapUtils.isEmpty(schemeMap)) {
                return;
            }
            CbxItem selectItem = (CbxItem) schemeCbx.getSelectedItem();
            MoveFileScheme scheme = schemeMap.get(selectItem.getKey());
            String[] dirsLines = ComponentUtil.getTextAreaContents(srcDirs);
            MapUtil.fillMapWithStringArray(scheme.getDirs(), dirsLines);
        });
        addComp(mdfBtn);
        delBtn = new JButton("[删除当前方案]");
        delBtn.addActionListener(e -> {
            if (schemeCbx.getItemCount() == 0) {
                return;
            }
            int idx = schemeCbx.getSelectedIndex();
            CbxItem selectItem = (CbxItem) schemeCbx.getSelectedItem();
            schemeCbx.removeItemAt(idx);
            schemeMap.remove(selectItem.getKey());
            srcDirs.setText("");
            if (schemeCbx.getItemCount() > 0) {
                schemeCbx.setSelectedIndex(0);
            }
        });
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
                    CbxItem item = new CbxItem(line);

                    String[] dirsLines = ComponentUtil.getTextAreaContents(srcDirs);

                    MoveFileScheme scheme = new MoveFileScheme();
                    scheme.setId(item.getKey());
                    scheme.setDesc(item.getValue());
                    MapUtil.fillMapWithStringArray(scheme.getDirs(), dirsLines);
                    schemeMap.put(scheme.getId(), scheme);

                    schemeCbx.addItem(item);
                    break;
                }
            }).showModel();
        });
        addComp(saveBtn);
        commitCurCompToPanel();
    }

    private void initSavedCbx() {
        schemeMap = MoveFileScheme.loadSchemeFromXml();
        schemeCbx.removeAllItems();
        for (Map.Entry<String, MoveFileScheme> entry : schemeMap.entrySet()) {
            schemeCbx.addItem(new CbxItem(entry.getKey(), entry.getValue().getDesc()));
        }
    }

    private void paintSrcArea() {
        srcLbl = new JLabel("[请列出源目录和目标目录，之间用" + Punctuation.ARROW + "分隔，多个目录对应关系分多行列出]");
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
                line = fileMap.split(Punctuation.ARROW);
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
                MoveFileScheme.saveSchemeToXml(schemeMap);
            }
        });
    }

}
