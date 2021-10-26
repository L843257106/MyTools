package pers.liuhan.toolkit.forms.base;


import pers.liuhan.toolkit.constant.ResConstant;
import pers.liuhan.toolkit.factory.FunctionFactory;
import pers.liuhan.toolkit.interfaces.IMainFunction;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Map;

/**
 * @author liuhan19691
 */
public class MainForm extends BaseForm {

    public MainForm() {
        super();
        setTitle("Marish`s Tool");
        setJMenuBar(genMenuBar());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JMenuBar genMenuBar() {
        JMenu menu = genMainFuncMenu();

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);

        setJMenuBar(menuBar);
        return menuBar;
    }

    private JMenu genMainFuncMenu() {
        JMenu menu = new JMenu("主 要 功 能");
        menu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        Map<String, IMainFunction> functionMap = FunctionFactory.getBeansMap();
        for (Map.Entry<String, IMainFunction> functionEntry : functionMap.entrySet()) {
            IMainFunction iMainFunction = functionEntry.getValue();
            JMenuItem menuItem = new JMenuItem(iMainFunction.getFunctionName());
            menuItem.addActionListener(e -> {
                BaseForm form = iMainFunction.getFunctionForm();
                form.setTitle(iMainFunction.getFunctionName());
                form.showModel();
            });
            menu.add(menuItem);
        }
        return menu;
    }
}
