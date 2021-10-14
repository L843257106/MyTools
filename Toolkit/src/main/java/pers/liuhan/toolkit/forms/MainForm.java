package pers.liuhan.toolkit.forms;


import pers.liuhan.toolkit.factory.FunctionFactory;
import pers.liuhan.toolkit.interfaces.IMainFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Map;

/**
 * @author liuhan19691
 */
public class MainForm extends BaseForm {

    public MainForm() {
        super();
        setTitle("Liuhan`s Tool");
        setJMenuBar(genMenuBar());
        setFreeFormWhenClose();
    }

    private JMenuBar genMenuBar() {
        JMenu menu = genMainFuncMenu();

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);

        setJMenuBar(menuBar);
        return menuBar;
    }

    private JMenu genMainFuncMenu() {
        JMenu menu = new JMenu("Main Functions");
        menu.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        Map<String, IMainFunction> functionMap = FunctionFactory.getBeansMap();
        for (Map.Entry<String, IMainFunction> functionEntry : functionMap.entrySet()) {
            IMainFunction function = functionEntry.getValue();
            JMenuItem menuItem = new JMenuItem(functionEntry.getKey());
            menuItem.addActionListener(e -> {
                BaseForm form = function.getFunctionForm();
                form.setTitle(functionEntry.getKey());
                form.showModel();
            });
            menu.add(menuItem);
        }
        return menu;
    }

    private void setFreeFormWhenClose() {
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }


}
