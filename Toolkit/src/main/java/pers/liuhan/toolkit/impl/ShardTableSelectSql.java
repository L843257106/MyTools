package pers.liuhan.toolkit.impl;


import org.springframework.stereotype.Component;
import pers.liuhan.toolkit.forms.ShardTableSelectSqlForm;
import pers.liuhan.toolkit.forms.base.BaseForm;
import pers.liuhan.toolkit.interfaces.IMainFunction;

/**
 * @author liuhan19691
 */
@Component("ShardTableSelectSql")
public class ShardTableSelectSql implements IMainFunction {


    @Override
    public String getFunctionName() {
        return "拼接分库分表SQL";
    }

    @Override
    public BaseForm getFunctionForm() {
        return new ShardTableSelectSqlForm();
    }
}
