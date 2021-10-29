package pers.liuhan.toolkit.forms.base;


import pers.liuhan.toolkit.component.factory.ButtonFactory;
import pers.liuhan.toolkit.factory.FunctionFactory;
import pers.liuhan.toolkit.interfaces.IMainFunction;
import pers.liuhan.toolkit.manager.SysLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

/**
 * @author liuhan19691
 */
public class MainForm extends BaseForm {

    JMenuBar menuBar;

    private JTextArea logScreen;
    private JScrollPane logPane;

    private JButton clearBtn;

    public MainForm() {
        super();
        setTitle("Marish`s Tool");
        setJMenuBar(genMenuBar());
        paintPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addFormEvent();
        SysLog.init(logScreen);
    }

    private JMenuBar genMenuBar() {
        JMenu menu = genMainFuncMenu();

        menuBar = new JMenuBar();
        menuBar.add(menu);

        setJMenuBar(menuBar);
        return menuBar;
    }

    private void paintPanel() {
        logScreen = new JTextArea();
        logPane = new JScrollPane(logScreen);
        logPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        logPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        clearBtn = ButtonFactory.getButton("清空日志");
        clearBtn.setBounds(getFactWidth() / 3, getFactHeight() / 10 * 8, getFactWidth() / 3, getFactHeight() / 10);


        logPane.setBounds(5, 0, getFactWidth() - 10, getFactHeight() / 10 * 7);
        mainPnl.add(logPane);
        mainPnl.add(clearBtn);
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

    private void addFormEvent() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                SysLog.close();
            }
        });
    }
}
