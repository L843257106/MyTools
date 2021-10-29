package pers.liuhan.toolkit.forms;

import pers.liuhan.toolkit.forms.base.BaseForm;

import javax.swing.*;

/**
 * @author liuhan19691
 */
public class ShardTableSelectSqlForm extends BaseForm {

    private JLabel shardNumLbl;
    private JTextField shardNumTf;


    public ShardTableSelectSqlForm() {
        super();
        paintShardInfo();

        paintForm();
    }

    private void paintShardInfo() {
        shardNumLbl = new JLabel();
        addComp(shardNumLbl);
        shardNumTf = new JTextField();
        addComp(shardNumTf);
        commitCurCompToPanel();
    }
}
