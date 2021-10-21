package pers.liuhan.toolkit.util;

import pers.liuhan.toolkit.forms.base.BaseForm;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author liuhan19691
 */
public class ComponentUtil {

    public static final int DEFAULT_HEIGHT = 25;

    public static final int COMP_BLANK = 10;

    public static void paintPanel(BaseForm form, JPanel panel, List<List<Component>> comps) {
        int x = COMP_BLANK;
        int y = COMP_BLANK;
        int width;
        for (List<Component> line : comps) {
            if (line.size() == 0) {
                continue;
            }
            width = (form.getFactWidth() - COMP_BLANK * (line.size() + 1)) / line.size();
            for (Component comp : line) {
                formatComponent(comp);
                comp.setBounds(x, y, width, DEFAULT_HEIGHT);
                panel.add(comp);
                x = x + width + COMP_BLANK;
            }
            x = COMP_BLANK;
            y = y + COMP_BLANK + DEFAULT_HEIGHT;
        }
    }

    public static void formatComponent(Component component) {
        if (component instanceof JTextField || component instanceof JTextArea) {
            component.setFont(new Font("黑体", Font.BOLD, 20));
        } else if (component instanceof JLabel) {
            ((JLabel) component).setHorizontalAlignment(SwingConstants.RIGHT);
            ((JLabel) component).setBorder(BorderFactory.createLineBorder(Color.black));
        }
    }
}
