package pers.liuhan.toolkit.forms;


import javax.swing.*;
import java.awt.*;

/**
 * @author liuhan19691
 */
public class TextForm extends BaseForm {

    private final int WIDTH_PERCENT = 75;
    private final int HEIGHT_PERCENT = 50;

    public TextForm(String text) {
        super();
        resetFormSize(WIDTH_PERCENT, HEIGHT_PERCENT);
        setFrameToScreenCenter();
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("黑体", Font.BOLD, 20));
        textArea.append(text);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);

        setAlwaysOnTop(true);
    }

    @Override
    public void showForm() {
        setModal(true);
        super.showForm();
    }
}
