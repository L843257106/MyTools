package pers.liuhan.toolkit.forms;


import pers.liuhan.toolkit.factory.FunctionFactory;
import pers.liuhan.toolkit.interfaces.IMainFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Map;
import java.util.stream.Collectors;

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
        JMenuBar menuBar = new JMenuBar();
        JMenuItem menuItem = new JMenuItem("item1");
        String a = "a";
        menuItem.addActionListener(e -> {
            TextForm textForm = new TextForm(a);
            textForm.showForm();
        });

        JMenu menu = new JMenu("main menu");
        menu.add(menuItem);
        menu.setBorder(BorderFactory.createLineBorder(Color.RED,1));

        menuBar.add(menu);

        setJMenuBar(menuBar);

        return menuBar;
    }

    private JMenu genMainFuncMenu() {
        JMenu menu = new JMenu("Main Functions");
        Map<String, IMainFunction> functionMap = FunctionFactory.getBeansMap();
        for (Map.Entry<String, IMainFunction> functionEntry : functionMap.entrySet()) {

        }
        JMenuItem menuItem = new JMenuItem("Text window");
        return menu;
    }

    private void setFreeFormWhenClose(){
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
