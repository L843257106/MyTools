package pers.liuhan.toolkit.impl;


import org.springframework.stereotype.Component;
import pers.liuhan.toolkit.forms.MoveFileForm;
import pers.liuhan.toolkit.forms.base.BaseForm;
import pers.liuhan.toolkit.interfaces.IMainFunction;

/**
 * @author liuhan19691
 */
@Component("BatchMoveFile")
public class BatchMoveFile implements IMainFunction {

    @Override
    public String getFunctionName() {
        return "批量移动文件";
    }

    @Override
    public BaseForm getFunctionForm() {
        return new MoveFileForm();
    }
}
