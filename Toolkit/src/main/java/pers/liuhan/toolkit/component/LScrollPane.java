package pers.liuhan.toolkit.component;


import javax.swing.*;
import java.awt.*;

/**
 *
 * @author liuhan19691
 */
public class LScrollPane extends JScrollPane {

    public static final int DEFAULT_HEIGHT_UNIT = 5;

    private int heightUnit;

    public LScrollPane(Component view, int heightUnit) {
        super(view);
        this.heightUnit = heightUnit;
    }

    public int getHeightUnit() {
        return heightUnit;
    }

    public void setHeightUnit(int heightUnit) {
        this.heightUnit = heightUnit;
    }
}
