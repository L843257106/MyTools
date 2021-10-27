package pers.liuhan.toolkit.component;

import pers.liuhan.toolkit.interfaces.IInputTextHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author liuhan19691
 */
public class InputTextForm extends BaseTextForm {

    IInputTextHandler handler;

    public InputTextForm(IInputTextHandler handler) {
        super();
        setTitle("请输入");
        this.handler = handler;
    }

    @Override
    protected ActionListener genOkBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.dealInputText(textArea);
                setVisible(false);
            }
        };
    }

}
