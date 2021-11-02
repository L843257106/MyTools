package pers.liuhan.toolkit.forms;

import org.apache.commons.lang3.StringUtils;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author liuhan19691
 */
public class ShardTableForm extends BaseForm {

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

    private JLabel fundAccLbl;
    private JTextField fundAccTf;
    private JButton fundAccBtn;

    private JLabel dbIdxLbl;
    private JTextField dbIdxTf;
    private JLabel tableIdxLbl;
    private JTextField tableIdxTf;


    private StringBuilder sqlContext;

    public ShardTableForm() {
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
        paintFundAccInfo();
        paintOwnTableInfo();
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

    private void paintFundAccInfo() {
        fundAccLbl = new JLabel("基金账号:");
        addComp(fundAccLbl);

        fundAccTf = new JTextField();
        addComp(fundAccTf);

        fundAccBtn = ButtonFactory.getButton("计算分库分表号");
        fundAccBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String assetAcc = fundAccTf.getText();
                int len = StringUtils.length(assetAcc);
                if (len < 8) {
                    dbIdxTf.setText("基金账号小于8位，无法计算分库分表信息!");
                    return;
                }

                int dbNum = NumberUtil.getIntFromString(shardNumTf.getText());
                int tableNum = NumberUtil.getIntFromString(tableNumTf.getText());
                String accHash = assetAcc.substring(len - 8);
                int hash = 0;
                len = accHash.length();
                int accChar;
                int charNum;
                for (int i = 0; i < len; ++i) {
                    accChar = accHash.charAt(i);
                    charNum = 0;
                    if (Character.isDigit(accChar)) {
                        charNum = accChar - '0';
                    } else if (Character.isUpperCase(accChar)) {
                        charNum = (accChar - 'A') % 10;
                    } else if (Character.isLowerCase(accChar)) {
                        charNum = (accChar - 'a') % 10;
                    }

                    hash = hash * 10 + charNum;
                }
                hash = Math.abs(hash);

                dbIdxTf.setText(String.valueOf((hash / dbNum) % tableNum + 1));
                tableIdxTf.setText(String.valueOf((hash % dbNum) + 1));
                SysLog.addLog("计算账号[" + assetAcc + "]的分库分表信息.");
            }
        });
        addComp(fundAccBtn);
        commitCurCompToPanel();
    }

    private void paintOwnTableInfo() {
        dbIdxLbl = new JLabel("分库号:");
        addComp(dbIdxLbl);
        dbIdxTf = new JTextField();
        addComp(dbIdxTf);

        tableIdxLbl = new JLabel("分表号:");
        addComp(tableIdxLbl);
        tableIdxTf = new JTextField();
        addComp(tableIdxTf);

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
