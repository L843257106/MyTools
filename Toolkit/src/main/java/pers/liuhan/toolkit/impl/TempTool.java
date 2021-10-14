package pers.liuhan.toolkit.impl;

import org.springframework.stereotype.Service;
import pers.liuhan.toolkit.component.BaseTextForm;
import pers.liuhan.toolkit.component.InputTextForm;
import pers.liuhan.toolkit.forms.BaseForm;
import pers.liuhan.toolkit.interfaces.IMainFunction;

/**
 * @author liuhan19691
 */

@Service("TempTool")
public class TempTool implements IMainFunction {

    @Override
    public String getFunctionName() {
        return "临时功能";
    }

    @Override
    public BaseForm getFunctionForm() {
        BaseTextForm inForm = new InputTextForm(0);
        return inForm;
    }

}
