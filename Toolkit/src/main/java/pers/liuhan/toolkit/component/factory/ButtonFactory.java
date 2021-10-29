package pers.liuhan.toolkit.component.factory;


import javax.swing.*;

public class ButtonFactory {

    private static final String LEFT_BORDER = "[";
    private static final String RIGHT_BORDER = "]";

    public static JButton getButton(String btnTitle) {
        return new JButton(LEFT_BORDER + btnTitle + RIGHT_BORDER);
    }

    public static JButton getButton() {
        return new JButton();
    }

}
