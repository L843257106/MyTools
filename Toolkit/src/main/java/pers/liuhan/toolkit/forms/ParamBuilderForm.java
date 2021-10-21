package pers.liuhan.toolkit.forms;


import pers.liuhan.toolkit.component.CbxItem;
import pers.liuhan.toolkit.component.InputTextForm;
import pers.liuhan.toolkit.component.OutTextForm;
import pers.liuhan.toolkit.forms.base.BaseForm;
import pers.liuhan.toolkit.util.ComponentUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuhan19691
 */
public class ParamBuilderForm extends BaseForm {
    private JPanel mainPnl;

    private JLabel oldCodeLbl = new JLabel("老系统参数代码:");
    private JTextField oldCodeText = new JTextField();
    private JLabel oldNameLbl = new JLabel("老系统参数名称:", SwingConstants.RIGHT);
    private JTextField oldNameText = new JTextField();

    private JLabel newCodeLbl = new JLabel("新系统参数代码:");
    private JTextField newCodeText = new JTextField();

    private JLabel visiableLbl = new JLabel("参数是否可见:");
    private JComboBox<CbxItem> visiableCbx = new JComboBox<>();

    private JLabel readOnlyLbl = new JLabel("参数是否只读:");
    private JComboBox<CbxItem> readOnluCbx = new JComboBox<>();

    private JLabel compTypeLbl = new JLabel("组件类型:");
    private JComboBox<CbxItem> compTypeCbx = new JComboBox<>();

    private JLabel groupIdLbl = new JLabel("系统参数所属组代码:");
    private JTextField groupIdText = new JTextField();

    private JLabel groupNameLbl = new JLabel("系统参数所属组名称:");
    private JTextField groupNameText = new JTextField();

    private JLabel sysTypeLbl = new JLabel("系统类型:");
    private JComboBox<CbxItem> sysTypeCbx = new JComboBox<>();

    private JLabel dictItemsLbl = new JLabel("数据字典类型:");
    private JComboBox<CbxItem> dictItemCbx = new JComboBox<>();

    private JLabel sysItemsLbl = new JLabel("系统参数项:");
    private JComboBox<CbxItem> sysItemCbx = new JComboBox<>();

    private JLabel sysValueLbl = new JLabel("参数值:");
    private JTextField sysValueText = new JTextField();

    private JLabel defaultSysValueLbl = new JLabel("参数默认值:");
    private JTextField defaultSysValueText = new JTextField();

    private JLabel orderLbl = new JLabel("排序值:");
    private JTextField orderText = new JTextField();

    private JButton resultBtn;

    private StringBuilder sqlContext;
    private List<List<Component>> components;


    public ParamBuilderForm() {
        super();
        components = new ArrayList<>();
        sqlContext = new StringBuilder();
        genMainPanel();
        genOldParamInfo();
        genNewParamInfo();
        genVisiableInfo();
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
        ComponentUtil.paintPanel(this, mainPnl, components);
    }

    private void genMainPanel() {
        mainPnl = new JPanel();
        mainPnl.setLayout(null);
        add(mainPnl);
    }

    private void genOldParamInfo() {
        List<Component> comps = new ArrayList<>();
        comps.add(oldCodeLbl);
        comps.add(oldCodeText);
        components.add(comps);

        comps = new ArrayList<>();
        comps.add(oldNameLbl);
        comps.add(oldNameText);
        components.add(comps);
    }

    private void genNewParamInfo() {
        List<Component> comps = new ArrayList<>();
        comps.add(newCodeLbl);
        comps.add(newCodeText);
        components.add(comps);
    }

    private void genVisiableInfo() {
        visiableCbx.addItem(new CbxItem("0", "否"));
        visiableCbx.addItem(new CbxItem("1", "是"));
        List<Component> comps = new ArrayList<>();
        comps.add(visiableLbl);
        comps.add(visiableCbx);
        components.add(comps);
    }

    private void genReadOnlyInfo() {
        readOnluCbx.addItem(new CbxItem("0", "否"));
        readOnluCbx.addItem(new CbxItem("1", "是"));
        List<Component> comps = new ArrayList<>();
        comps.add(readOnlyLbl);
        comps.add(readOnluCbx);
        components.add(comps);
    }

    private void genCompTypeInfo() {
        compTypeCbx.addItem(new CbxItem("1", "文本输入框"));
        compTypeCbx.addItem(new CbxItem("Y", "ratio按钮"));
        compTypeCbx.addItem(new CbxItem("A", "下拉框"));
        List<Component> comps = new ArrayList<>();
        comps.add(compTypeLbl);
        comps.add(compTypeCbx);
        components.add(comps);
    }

    private void genGroupIdInfo() {
        List<Component> comps = new ArrayList<>();
        comps.add(groupIdLbl);
        comps.add(groupIdText);
        components.add(comps);
    }

    private void genGroupNameInfo() {
        List<Component> comps = new ArrayList<>();
        comps.add(groupNameLbl);
        comps.add(groupNameText);
        components.add(comps);
    }

    private void genSysTypeInfo() {
        sysTypeCbx.addItem(new CbxItem("4", "ETF系统"));
        sysTypeCbx.addItem(new CbxItem("6", "分TA系统"));

        List<Component> comps = new ArrayList<>();
        comps.add(sysTypeLbl);
        comps.add(sysTypeCbx);
        components.add(comps);
    }

    private void genDictItemsInfo() {
        dictItemCbx.addItem(new CbxItem("0", "0:否/1:是"));
        dictItemCbx.addItem(new CbxItem("1", "0:不开通/1:开通"));
        dictItemCbx.addItem(new CbxItem("Z", "自定义"));

        dictItemCbx.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                CbxItem item = (CbxItem) e.getItem();
                sysItemCbx.removeAllItems();
                if ("Z".equals(item.getKey())) {
                    InputTextForm textForm = new InputTextForm(InputTextForm.FILL_CBX);
                    textForm.setTextBoot(sysItemCbx);
                    textForm.showModel();
                } else {
                    List<String> items = Arrays.asList(item.getValue().split("/"));
                    for (String value : items) {
                        CbxItem tmpItem = new CbxItem(value);
                        sysItemCbx.addItem(tmpItem);
                    }
                }
            }
        });

        List<Component> comps = new ArrayList<>();
        comps.add(dictItemsLbl);
        comps.add(dictItemCbx);
        comps.add(sysItemsLbl);
        comps.add(sysItemCbx);
        components.add(comps);
    }

    private void genDefaultSysValueInfo() {
        List<Component> comps = new ArrayList<>();
        comps.add(defaultSysValueLbl);
        comps.add(defaultSysValueText);
        components.add(comps);
    }

    private void genSysValueInfo() {
        List<Component> comps = new ArrayList<>();
        comps.add(sysValueLbl);
        comps.add(sysValueText);
        components.add(comps);
    }

    private void genOrderInfo() {
        List<Component> comps = new ArrayList<>();
        comps.add(orderLbl);
        comps.add(orderText);
        components.add(comps);
    }

    private void genResultButton() {
        resultBtn = new JButton("生成sql");
        resultBtn.addActionListener(e -> {
            genSqlContext();
            new OutTextForm(sqlContext.toString()).showModel();
        });
        List<Component> comps = new ArrayList<>();
        comps.add(resultBtn);
        components.add(comps);
    }

    private void genSqlContext() {
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
    }
}
