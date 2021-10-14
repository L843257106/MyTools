package pers.liuhan.toolkit.interfaces;

import pers.liuhan.toolkit.forms.BaseForm;

/**
 * @author liuhan19691
 */
public interface IMainFunction {

    /**
     * 显示功能窗口
     */
    void showFunctionForm();

    /**
     * 获取具体功能的窗口
     *
     * @return 具体功能的窗口
     */
    BaseForm getFunctionForm();

}
