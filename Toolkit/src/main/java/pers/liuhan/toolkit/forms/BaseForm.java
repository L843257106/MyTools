package pers.liuhan.toolkit.forms;


import javax.swing.*;
import java.awt.*;

/**
 * @author liuhan19691
 */
public class BaseForm extends JFrame {

    public static final int MAX_WIDTH_PERCENT = 100;
    public static final int MAX_HEIGHT_PERCENT = 100;

    private int factWidth;
    private int factHeight;

    public BaseForm() {
        setDefaultFrameSize();
        setFrameToScreenCenter();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
    }

    public void showForm() {
        setVisible(true);
    }

    public void showModel(){
        showForm();
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
        this.setSize(width, height);
    }

    public void resetFrameSize(int widthPercent, int heightPercent) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / MAX_WIDTH_PERCENT * widthPercent;
        int height = screenSize.height / MAX_HEIGHT_PERCENT * heightPercent;
        this.setSize(width, height);
    }

    @Override
    public void setSize(int width, int height) {
        this.factWidth = width;
        this.factHeight = height;
        super.setSize(width, height);
    }


    public int getFactWidth() {
        return factWidth;
    }

    public int getFactHeight() {
        return factHeight;
    }
}
