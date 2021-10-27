package pers.liuhan.toolkit.component;

import org.apache.commons.lang3.StringUtils;
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

    private static final String TEXT_AREA_END = "\n";

    public static void paintPanel(BaseForm form, JPanel panel, List<List<Component>> comps) {
        int x = COMP_BLANK;
        int y = COMP_BLANK;
        int width;
        for (List<Component> line : comps) {
            if (line.size() == 0) {
                continue;
            }
            width = (form.getFactWidth() - COMP_BLANK * (line.size() + 1)) / line.size();
            int height = DEFAULT_HEIGHT;
            for (Component comp : line) {
                height = formatComponent(comp);
                comp.setBounds(x, y, width, height);
                panel.add(comp);
                x = x + comp.getWidth() + COMP_BLANK;
            }
            x = COMP_BLANK;
            y = y + COMP_BLANK + height;
        }
    }

    public static int formatComponent(Component component) {
        int height = DEFAULT_HEIGHT;
        if (component instanceof JTextField || component instanceof JTextArea) {
            component.setFont(new Font("黑体", Font.BOLD, 25));
        } else if (component instanceof JLabel) {
            ((JLabel) component).setHorizontalAlignment(SwingConstants.RIGHT);
            ((JLabel) component).setBorder(BorderFactory.createLineBorder(Color.black));
        } else if (component instanceof LScrollPane) {
            ((LScrollPane) component).setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            ((LScrollPane) component).setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            height = ((LScrollPane) component).getHeightUnit() * DEFAULT_HEIGHT;
        }
        return height;
    }

    public static String[] getTextAreaContents(JTextArea textArea) {
        return textArea.getText().split(TEXT_AREA_END);
    }

    public static void fillCombobox(JComboBox<CbxItem> comboBox, String[] lines) {
        for (String line : lines) {
            if (StringUtils.isBlank(line)) {
                continue;
            }
            comboBox.addItem(new CbxItem(line));
        }
    }
}
