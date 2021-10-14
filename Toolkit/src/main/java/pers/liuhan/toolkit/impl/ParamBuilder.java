package pers.liuhan.toolkit.impl;


import org.springframework.stereotype.Component;
import pers.liuhan.toolkit.forms.BaseForm;
import pers.liuhan.toolkit.forms.ParamBuilderForm;
import pers.liuhan.toolkit.interfaces.IMainFunction;

/**
 * @author liuhan19691
 */
@Component("ParamBuilder")
public class ParamBuilder implements IMainFunction {

    @Override
    public String getFunctionName() {
        return "6.0系统参数生成器";
    }

    @Override
    public BaseForm getFunctionForm() {
        return new ParamBuilderForm();
    }

}
