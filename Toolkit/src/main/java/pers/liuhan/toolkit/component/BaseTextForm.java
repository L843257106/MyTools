package pers.liuhan.toolkit.component;


import pers.liuhan.toolkit.forms.base.BaseForm;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * @author liuhan19691
 */
public class BaseTextForm extends BaseForm {

    protected final int WIDTH_PERCENT = 80;
    protected final int HEIGHT_PERCENT = 50;

    private final int BLANK_LEN = 10;

    private JPanel mainPnl;
    private JScrollPane scrollPane;
    protected JTextArea textArea;
    protected JButton okBtn;

    public BaseTextForm() {
        super();
        resetFormSize(WIDTH_PERCENT, HEIGHT_PERCENT);
        setFrameToScreenCenter();
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

        genMainPanel();
        genScrollPane();
        genOkBtn();
    }

    private void genMainPanel() {
        mainPnl = new JPanel();
        mainPnl.setLayout(null);
        add(mainPnl);
    }

    private void genScrollPane() {
        textArea = new JTextArea();
       // textArea.setFont(new Font("黑体", Font.BOLD, 20));
        scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 0, getFactWidth(), getFactHeight() / 5 * 4);
        mainPnl.add(scrollPane);
    }

    private void genOkBtn() {
        okBtn = new JButton("[确定]");
        int btnWidth = getFactWidth() / 10;
        int btnHeight = 30;
        int x = getFactWidth() / 2 - btnWidth / 2;
        int y = getFactHeight() / 5 * 4 + BLANK_LEN;
        okBtn.setBounds(x, y, btnWidth, btnHeight);

        okBtn.addActionListener(genOkBtnListener());
        mainPnl.add(okBtn);
    }

    protected ActionListener genOkBtnListener() {
        return e -> setVisible(false);
    }


}
