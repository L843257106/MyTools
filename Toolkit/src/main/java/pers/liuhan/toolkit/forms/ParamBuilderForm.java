package pers.liuhan.toolkit.forms;


import pers.liuhan.toolkit.util.ComponentUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuhan19691
 */
public class ParamBuilderForm extends BaseForm {
    private JPanel mainPnl;

    private JLabel oldCodeLbl = new JLabel("2.0系统参数代码:");
    private JTextField oldCodeText = new JTextField();
    private JLabel oldNameLbl = new JLabel("2.0系统参数名称:", SwingConstants.RIGHT);
    private JTextField oldNameText = new JTextField();

    private JLabel newCodeLbl = new JLabel("6.0系统参数代码:");
    private JTextField newNameText = new JTextField();

    private JLabel readOnluLbl = new JLabel("参数是否只读:");
    private JComboBox<String> readOnluCbx = new JComboBox<>();

    private JLabel sysTypeLbl = new JLabel("系统类型:");
    private JComboBox<String> sysTypeCbx = new JComboBox<>();

    private JLabel compTypeLbl = new JLabel("组件类型:");
    private JComboBox<String> compTypeCbx = new JComboBox<>();

    private JLabel groupNameLbl = new JLabel("系统参数所属组名称:");
    private JTextField groupNameText = new JTextField();

    private JLabel dictItemsLbl = new JLabel("数据字典类型:");
    private JComboBox<String> dictItemCbx = new JComboBox<>();

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
        genReadOnlyInfo();
        genSysTypeInfo();
        genCompTypeInfo();
        genGroupNameInfo();
        genDictItemsInfo();

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
        comps.add(newNameText);
        components.add(comps);
    }

    private void genReadOnlyInfo() {
        readOnluCbx.addItem("0:否");
        readOnluCbx.addItem("1:是");
        List<Component> comps = new ArrayList<>();
        comps.add(readOnluLbl);
        comps.add(readOnluCbx);
        components.add(comps);
    }

    private void genSysTypeInfo() {
        sysTypeCbx.addItem("etf:ETF系统");
        sysTypeCbx.addItem("sub:分TA系统");
        List<Component> comps = new ArrayList<>();
        comps.add(sysTypeLbl);
        comps.add(sysTypeCbx);
        components.add(comps);
    }

    private void genCompTypeInfo() {
        compTypeCbx.addItem("1:文本输入框");
        compTypeCbx.addItem("Y:ratio按钮");
        compTypeCbx.addItem("A:下拉框");
        List<Component> comps = new ArrayList<>();
        comps.add(compTypeLbl);
        comps.add(compTypeCbx);
        components.add(comps);
    }

    private void genGroupNameInfo() {
        List<Component> comps = new ArrayList<>();
        comps.add(groupNameLbl);
        comps.add(groupNameText);
        components.add(comps);
    }

    private void genDictItemsInfo() {
        dictItemCbx.addItem("0:是/否");
        dictItemCbx.addItem("1:开通/不开通");
        dictItemCbx.addItem("Z:自定义");
        List<Component> comps = new ArrayList<>();
        comps.add(dictItemsLbl);
        comps.add(dictItemCbx);
        components.add(comps);
    }

    private void genResultButton() {
        resultBtn = new JButton("生成sql");
        resultBtn.addActionListener(e -> {
            genSqlContext();
            new TextForm(sqlContext.toString()).showModel();
        });
        List<Component> comps = new ArrayList<>();
        comps.add(resultBtn);
        components.add(comps);
    }

    private void genSqlContext() {
        sqlContext.setLength(0);
    }
}
