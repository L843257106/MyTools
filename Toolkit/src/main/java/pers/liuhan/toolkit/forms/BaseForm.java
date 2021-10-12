package pers.liuhan.toolkit.forms;


import javax.swing.*;
import java.awt.*;

/**
 * @author liuhan19691
 */
public class BaseForm extends JDialog {

    public static final int MAX_WIDTH_PERCENT = 100;
    public static final int MAX_HEIGHT_PERCENT = 100;

    public BaseForm() {
        setDefaultFrameSize();
        setFrameToScreenCenter();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    public void showForm() {
        setVisible(true);
    }

    public void resetFormSize(int widthPercent, int heightPercent) {
        resetFrameSize(widthPercent, heightPercent);
    }

    public void setFrameToScreenCenter() {
        Dimension formSize = this.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - formSize.width) / 2;
        int y = (screenSize.height - formSize.height) / 2;
        setLocation(x, y);
    }

    public void setDefaultFrameSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2;
        int height = screenSize.height / 2;
        setSize(width, height);
    }

    public void resetFrameSize(int widthPercent, int heightPercent) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / MAX_WIDTH_PERCENT * widthPercent;
        int height = screenSize.height / MAX_HEIGHT_PERCENT * heightPercent;
        setSize(width, height);
    }
}
