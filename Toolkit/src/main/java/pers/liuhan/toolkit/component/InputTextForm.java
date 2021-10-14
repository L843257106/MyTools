package pers.liuhan.toolkit.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author liuhan19691
 */
public class InputTextForm extends BaseTextForm {

    private Component textBoot;

    private int mode = 0;

    public static final int FILL_CBX = 1;

    public InputTextForm(int mode) {
        super();
        this.mode = mode;
        setTitle("请输入");
    }

    public InputTextForm() {
        super();
        setTitle("请输入");
    }

    public void setTextBoot(Component comp) {
        this.textBoot = comp;
    }

    @Override
    protected ActionListener genOkBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mode == FILL_CBX) {
                    fillCombos();
                    setVisible(false);
                }
            }
        };
    }

    private void fillCombos() {
        if (textBoot instanceof JComboBox) {
            String[] lines = textArea.getText().split("\n");
            ((JComboBox) textBoot).removeAllItems();
            for (String item : lines) {
                ((JComboBox) textBoot).addItem(new CbxItem(item));
            }
        }
    }
}
