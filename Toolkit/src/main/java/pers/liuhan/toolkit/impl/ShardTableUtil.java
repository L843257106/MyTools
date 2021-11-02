package pers.liuhan.toolkit.impl;


import org.springframework.stereotype.Component;
import pers.liuhan.toolkit.forms.ShardTableForm;
import pers.liuhan.toolkit.forms.base.BaseForm;
import pers.liuhan.toolkit.interfaces.IMainFunction;

/**
 * @author liuhan19691
 */
@Component("ShardTableUtil")
public class ShardTableUtil implements IMainFunction {


    @Override
    public String getFunctionName() {
        return "分库分表小工具";
    }

    @Override
    public BaseForm getFunctionForm() {
        return new ShardTableForm();
    }
}
