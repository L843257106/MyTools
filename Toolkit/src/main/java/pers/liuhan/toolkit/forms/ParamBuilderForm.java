package pers.liuhan.toolkit.forms;


import javax.swing.*;

/**
 * @author liuhan19691
 */
public class ParamBuilderForm extends BaseForm {
    public ParamBuilderForm() {
        super();
        paintForm();
    }

    private void paintForm() {
        paintPanel();
    }

    private JButton getResultButton() {
        JButton button = new JButton("Generate Sql");
        button.setBounds(150,50,50,50);
        String ok = "ok";

        button.addActionListener(e -> new TextForm(ok).showModel());
        return button;
    }

    private void paintPanel(){
        JPanel panel = new JPanel();
        panel.add(getResultButton());
        add(panel);
    }

}
