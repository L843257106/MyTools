package pers.liuhan.toolkit.component.factory;


import pers.liuhan.toolkit.component.CbxItem;

import javax.swing.*;

public class ComboBoxFactory {

    public static JComboBox<CbxItem> getYesOrNoComboBox() {
        JComboBox<CbxItem> result = new JComboBox<>();
        CbxItem noItem = new CbxItem("0", "否");
        CbxItem yesItem = new CbxItem("1", "是");
        result.addItem(noItem);
        result.addItem(yesItem);
        return result;
    }

}
