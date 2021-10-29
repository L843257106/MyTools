package pers.liuhan.toolkit.forms;

import pers.liuhan.toolkit.component.CbxItem;
import pers.liuhan.toolkit.component.factory.ButtonFactory;
import pers.liuhan.toolkit.component.factory.ComboBoxFactory;
import pers.liuhan.toolkit.forms.base.BaseForm;
import pers.liuhan.toolkit.forms.view.OutTextForm;
import pers.liuhan.toolkit.manager.SysLog;
import pers.liuhan.toolkit.util.BooleanUtil;
import pers.liuhan.toolkit.util.NumberUtil;
import pers.liuhan.toolkit.util.StringUtil;

import javax.swing.*;

/**
 * @author liuhan19691
 */
public class ShardTableSelectSqlForm extends BaseForm {

    private JLabel shardNumLbl;
    private JTextField shardNumTf;

    private JLabel shardDbPrefixLbl;
    private JTextField shardDbPrefixTf;

    private JLabel tableNameLbl;
    private JTextField tableNameTf;

    private JLabel mulTableLbl;
    private JComboBox<CbxItem> mulTableCbx;

    private JLabel tableNumLbl;
    private JTextField tableNumTf;

    private JButton okBtn;


    private StringBuilder sqlContext;

    public ShardTableSelectSqlForm() {
        super();
        sqlContext = new StringBuilder();
    }

    @Override
    protected void fillComponents() {
        paintShardInfo();
        paintShardDbPrefix();
        paintTableNameInfo();
        paintTableNumInfo();
        paintOk();
    }

    private void paintShardInfo() {
        shardNumLbl = new JLabel("分库数:");
        addComp(shardNumLbl);
        shardNumTf = new JTextField("2");
        addComp(shardNumTf);
        commitCurCompToPanel();
    }

    private void paintShardDbPrefix() {
        shardDbPrefixLbl = new JLabel("分库名称前缀:");
        addComp(shardDbPrefixLbl);
        shardDbPrefixTf = new JTextField("ta6etf");
        addComp(shardDbPrefixTf);
        commitCurCompToPanel();
    }

    private void paintTableNameInfo() {
        tableNameLbl = new JLabel("表名:");
        addComp(tableNameLbl);
        tableNameTf = new JTextField();
        addComp(tableNameTf);
        commitCurCompToPanel();
    }

    private void paintTableNumInfo() {
        mulTableLbl = new JLabel("是否分表:");
        addComp(mulTableLbl);
        mulTableCbx = ComboBoxFactory.getYesOrNoComboBox();
        addComp(mulTableCbx);

        tableNumLbl = new JLabel("分表数:");
        addComp(tableNumLbl);
        tableNumTf = new JTextField("16");
        addComp(tableNumTf);
        commitCurCompToPanel();
    }

    private void paintOk() {
        okBtn = ButtonFactory.getButton("生成sql");
        addComp(okBtn);
        okBtn.addActionListener(e -> {
            genContext();
            new OutTextForm(sqlContext.toString()).showModel();
        });
        commitCurCompToPanel();
    }

    private void genContext() {
        SysLog.addLog("正在生成分库分表查询语句...");
        int dbNum = NumberUtil.getIntFromString(shardNumTf.getText());
        String dbPrefix = shardDbPrefixTf.getText();
        int tableNum = NumberUtil.getIntFromString(tableNumTf.getText());
        boolean isMulTable = BooleanUtil.getBoolean(((CbxItem) mulTableCbx.getSelectedItem()).getKey());
        String tableName = tableNameTf.getText();

        String lineEnd = StringUtil.getLineEnd();
        String blank = StringUtil.getOneBlank();

        String selectStar = " select * from ";
        sqlContext.setLength(0);
        sqlContext.append(selectStar).append("(").append(lineEnd);
        for (int dbIdx = 1; dbIdx <= dbNum; dbIdx++) {
            if (dbIdx > 1) {
                sqlContext.append(" union all ").append(lineEnd);
            }
            String realDbName = dbPrefix + dbIdx;
            String realTableName = realDbName + StringUtil.getOnePoint() + tableName;
            if (isMulTable) {
                for (int tableIdx = 1; tableIdx <= tableNum; tableIdx++) {
                    realTableName = realDbName + StringUtil.getOnePoint() + tableName + tableIdx;
                    if (tableIdx > 1) {
                        sqlContext.append(" union all ").append(lineEnd);
                    }
                    sqlContext.append(blank).append(blank).append(selectStar).append(realTableName).append(lineEnd);
                }
            } else {
                sqlContext.append(blank).append(blank).append(selectStar).append(realTableName).append(lineEnd);
            }
        }
        sqlContext.append(") a ").append(lineEnd);
        SysLog.addLog("生成分库分表查询语句结束");
    }

}
