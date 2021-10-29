package pers.liuhan.toolkit.component.factory;


import pers.liuhan.toolkit.component.CbxItem;

import javax.swing.*;

/**
 * @author liuhan19691
 */
public class ComboBoxFactory {
    public static JComboBox<CbxItem> getYesOrNoComboBox() {
        JComboBox<CbxItem> result = new JComboBox<>();
        result.addItem(new CbxItem("0", "否"));
        result.addItem(new CbxItem("1", "是"));
        return result;
    }
}
