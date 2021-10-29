package pers.liuhan.toolkit.forms;

import pers.liuhan.toolkit.component.CbxItem;
import pers.liuhan.toolkit.component.factory.ComboBoxFactory;
import pers.liuhan.toolkit.forms.base.BaseForm;

import javax.swing.*;

/**
 * @author liuhan19691
 */
public class ShardTableSelectSqlForm extends BaseForm {

    private JLabel shardNumLbl;
    private JTextField shardNumTf;

    private JLabel tableNameLbl;
    private JTextField tableNameTf;

    private JLabel mulTableLbl;
    private JComboBox<CbxItem> mulTableCbx;

    private JLabel tableNumLbl;
    private JTextField tableNumTf;


    private StringBuilder sqlContext;

    public ShardTableSelectSqlForm() {
        super();
        sqlContext = new StringBuilder();
    }

    @Override
    protected void fillComponents() {
        paintShardInfo();
        paintTableNameInfo();
        paintTableNumInfo();
    }

    private void paintShardInfo() {
        shardNumLbl = new JLabel("分库数:");
        addComp(shardNumLbl);
        shardNumTf = new JTextField("2");
        addComp(shardNumTf);
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


}
