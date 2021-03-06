package pers.liuhan.toolkit.forms;


import pers.liuhan.toolkit.component.CbxItem;
import pers.liuhan.toolkit.component.ComponentUtil;
import pers.liuhan.toolkit.component.factory.ComboBoxFactory;
import pers.liuhan.toolkit.forms.base.BaseForm;
import pers.liuhan.toolkit.forms.view.InputTextForm;
import pers.liuhan.toolkit.forms.view.OutTextForm;
import pers.liuhan.toolkit.manager.SysLog;

import javax.swing.*;
import java.awt.event.ItemEvent;

/**
 * @author liuhan19691
 */
public class ParamBuilderForm extends BaseForm {

    private JLabel oldCodeLbl;
    private JTextField oldCodeText;
    private JLabel oldNameLbl;
    private JTextField oldNameText;

    private JLabel newCodeLbl;
    private JTextField newCodeText = new JTextField();

    private JLabel visiableLbl;
    private JComboBox<CbxItem> visiableCbx;

    private JLabel readOnlyLbl;
    private JComboBox<CbxItem> readOnluCbx;

    private JLabel compTypeLbl;
    private JComboBox<CbxItem> compTypeCbx;

    private JLabel groupIdLbl;
    private JTextField groupIdText;

    private JLabel groupNameLbl;
    private JTextField groupNameText;

    private JLabel sysTypeLbl;
    private JComboBox<CbxItem> sysTypeCbx;

    private JLabel dictItemsLbl;
    private JComboBox<CbxItem> dictItemCbx;
    private JLabel sysItemsLbl;
    private JComboBox<CbxItem> sysItemCbx;

    private JLabel sysValueLbl;
    private JTextField sysValueText;

    private JLabel defaultSysValueLbl;
    private JTextField defaultSysValueText;

    private JLabel orderLbl;
    private JTextField orderText;

    private JButton resultBtn;

    private StringBuilder sqlContext;

    public ParamBuilderForm() {
        super();
        sqlContext = new StringBuilder();
    }

    @Override
    protected void fillComponents() {
        genOldParamInfo();
        genNewParamInfo();
        genVisibleInfo();
        genReadOnlyInfo();
        genCompTypeInfo();
        genGroupIdInfo();
        genGroupNameInfo();
        genSysTypeInfo();
        genDictItemsInfo();
        genSysValueInfo();
        genDefaultSysValueInfo();
        genOrderInfo();

        genResultButton();
    }

    private void genOldParamInfo() {
        oldCodeLbl = new JLabel("?????????????????????:");
        addComp(oldCodeLbl);
        oldCodeText = new JTextField();
        addComp(oldCodeText);
        commitCurCompToPanel();

        oldNameLbl = new JLabel("?????????????????????:");
        addComp(oldNameLbl);
        oldNameText = new JTextField();
        addComp(oldNameText);
        commitCurCompToPanel();
    }

    private void genNewParamInfo() {
        newCodeLbl = new JLabel("?????????????????????:");
        addComp(newCodeLbl);
        newCodeText = new JTextField();
        addComp(newCodeText);
        commitCurCompToPanel();
    }

    private void genVisibleInfo() {
        visiableLbl = new JLabel("??????????????????:");
        addComp(visiableLbl);
        visiableCbx = ComboBoxFactory.getYesOrNoComboBox();
        addComp(visiableCbx);
        commitCurCompToPanel();
    }

    private void genReadOnlyInfo() {
        readOnlyLbl = new JLabel("??????????????????:");
        addComp(readOnlyLbl);

        readOnluCbx = ComboBoxFactory.getYesOrNoComboBox();
        addComp(readOnluCbx);
        commitCurCompToPanel();
    }

    private void genCompTypeInfo() {
        compTypeLbl = new JLabel("????????????:");
        addComp(compTypeLbl);

        compTypeCbx = new JComboBox<>();
        compTypeCbx.addItem(new CbxItem("1", "???????????????"));
        compTypeCbx.addItem(new CbxItem("Y", "ratio??????"));
        compTypeCbx.addItem(new CbxItem("A", "?????????"));
        addComp(compTypeCbx);
        commitCurCompToPanel();
    }

    private void genGroupIdInfo() {
        groupIdLbl = new JLabel("???????????????????????????:");
        addComp(groupIdLbl);
        groupIdText = new JTextField();
        addComp(groupIdText);
        commitCurCompToPanel();
    }

    private void genGroupNameInfo() {
        groupNameLbl = new JLabel("???????????????????????????:");
        addComp(groupNameLbl);
        groupNameText = new JTextField();
        addComp(groupNameText);
        commitCurCompToPanel();
    }

    private void genSysTypeInfo() {
        sysTypeLbl = new JLabel("????????????:");
        addComp(sysTypeLbl);

        sysTypeCbx = new JComboBox<>();
        sysTypeCbx.addItem(new CbxItem("4", "ETF??????"));
        sysTypeCbx.addItem(new CbxItem("6", "???TA??????"));
        addComp(sysTypeCbx);
        commitCurCompToPanel();
    }

    private void genDictItemsInfo() {


        dictItemsLbl = new JLabel("??????????????????:");
        addComp(dictItemsLbl);
        dictItemCbx = new JComboBox<>();
        dictItemCbx.addItem(new CbxItem("0", "0:???/1:???"));
        dictItemCbx.addItem(new CbxItem("1", "0:?????????/1:??????"));
        dictItemCbx.addItem(new CbxItem("Z", "?????????"));
        dictItemCbx.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                CbxItem item = (CbxItem) e.getItem();
                sysItemCbx.removeAllItems();
                if ("Z".equals(item.getKey())) {
                    new InputTextForm(textArea -> {
                        String[] lines = ComponentUtil.getTextAreaContents(textArea);
                        ComponentUtil.fillCombobox(sysItemCbx, lines);
                    }).showModel();
                } else {
                    String[] items = item.getValue().split("/");
                    for (String value : items) {
                        CbxItem tmpItem = new CbxItem(value);
                        sysItemCbx.addItem(tmpItem);
                    }
                }
            }
        });
        addComp(dictItemCbx);
        sysItemsLbl = new JLabel("???????????????:");
        addComp(sysItemsLbl);
        sysItemCbx = new JComboBox<>();
        addComp(sysItemCbx);
        commitCurCompToPanel();
    }

    private void genSysValueInfo() {
        sysValueLbl = new JLabel("?????????:");
        addComp(sysValueLbl);
        sysValueText = new JTextField();
        addComp(sysValueText);
        commitCurCompToPanel();
    }

    private void genDefaultSysValueInfo() {
        defaultSysValueLbl = new JLabel("???????????????:");
        addComp(defaultSysValueLbl);
        defaultSysValueText = new JTextField();
        addComp(defaultSysValueText);
        commitCurCompToPanel();
    }

    private void genOrderInfo() {
        orderLbl = new JLabel("?????????:");
        addComp(orderLbl);
        orderText = new JTextField();
        addComp(orderText);
        commitCurCompToPanel();
    }

    private void genResultButton() {
        resultBtn = new JButton("??????sql");
        resultBtn.addActionListener(e -> {
            genSqlContext();
            new OutTextForm(sqlContext.toString()).showModel();
        });
        addComp(resultBtn);
        commitCurCompToPanel();
    }

    private void genSqlContext() {
        SysLog.addLog("????????????????????????Sql.....");
        String lineEnd = "\n";
        sqlContext.setLength(0);
        String insertTbparamSql = "insert into tbparam (ta_code, param_id, param_name, param_value, value_name, belong_type, modi_flag, reserve1)";
        String oldParam = oldCodeText.getText();
        String oldName = oldNameText.getText();
        String newParam = newCodeText.getText();
        String sysType = ((CbxItem) sysTypeCbx.getSelectedItem()).getKey();
        String itemsKeyCode = "000000" + "_" + sysType + "_" + oldParam;

        String visible = ((CbxItem) visiableCbx.getSelectedItem()).getKey();
        String readOnly = ((CbxItem) readOnluCbx.getSelectedItem()).getKey();
        String compType = ((CbxItem) compTypeCbx.getSelectedItem()).getKey();
        String groupId = groupIdText.getText();
        String groupName = groupNameText.getText();
        String sysValue = sysValueText.getText();
        String defaultValue = defaultSysValueText.getText();
        String order = orderText.getText();


        sqlContext.append("-- ").append(oldName).append("(").append(oldParam).append(")").append(lineEnd);

        sqlContext.append("--delete from tbparam where param_id = '").append(oldParam).append("' ");
        sqlContext.append("and belong_type = '").append(sysType).append("';").append(lineEnd);

        sqlContext.append(insertTbparamSql).append(lineEnd);
        sqlContext.append("values ('000000', '").append(newParam).append("', '").append(oldName).append("', '").append(sysValue);
        sqlContext.append("', ' ', '").append(sysType).append("', '1', ' ');").append(lineEnd);

        sqlContext.append("").append(lineEnd);
        sqlContext.append("--delete from tbparamelement where param_id = '").append(oldParam).append("' ");
        sqlContext.append("and belong_type = '").append(sysType).append("';").append(lineEnd);

        sqlContext.append("insert into tbparamelement(belong_type, ta_code, param_id, param_name, element_id, component_kind, group_id, group_name, order_no, data_default, prompt, split_str, visable, readonly_flag, line_flag, on_change) ").append(lineEnd);
        sqlContext.append("values ('").append(sysType).append("', '000000', '").append(newParam).append("', '").append(oldName).append("', '");
        sqlContext.append(itemsKeyCode).append("', '").append(compType).append("', ").append(groupId).append(", '");
        sqlContext.append(groupName).append("', ").append(order).append(", '").append(defaultValue).append("', '");
        sqlContext.append(oldName).append("', ' ', '").append(visible).append("', '").append(readOnly).append("', '0', ' ');").append(lineEnd);

        sqlContext.append(lineEnd);
        sqlContext.append("--delete from tbparamelementdata where element_id = '").append(itemsKeyCode).append("';").append(lineEnd);

        int totalCount = sysItemCbx.getItemCount();
        for (int i = 0; i < totalCount; i++) {
            CbxItem item = sysItemCbx.getItemAt(i);
            sqlContext.append("insert into tbparamelementdata(element_id, order_no, param_value, value_name, dict_key, visable, readonly_flag, check_format, data_width)").append(lineEnd);
            sqlContext.append("values ('").append(itemsKeyCode).append("', ").append(i).append(", '").append(item.getKey()).append("', '");
            sqlContext.append(item.getValue()).append("', ' ', '1', '0', ' ', 0);").append(lineEnd);
        }
        SysLog.addLog("??????????????????Sql??????");
    }
}
