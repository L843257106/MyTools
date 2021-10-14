package pers.liuhan.toolkit.impl;

import org.springframework.stereotype.Service;
import pers.liuhan.toolkit.forms.BaseForm;
import pers.liuhan.toolkit.interfaces.IMainFunction;

/**
 * @author liuhan19691
 */

@Service("TempTool")
public class TempTool implements IMainFunction {

    @Override
    public void showFunctionForm() {

    }

    @Override
    public BaseForm getFunctionForm() {
        return new BaseForm();
    }

}
