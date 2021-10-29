package pers.liuhan.toolkit.forms.view;


import pers.liuhan.toolkit.forms.base.BaseTextForm;

/**
 * @author liuhan19691
 */
public class OutTextForm extends BaseTextForm {

    public OutTextForm(String text) {
        super();
        textArea.append(text);
    }

}
