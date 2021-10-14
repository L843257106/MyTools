package pers.liuhan.toolkit.interfaces;

import pers.liuhan.toolkit.forms.BaseForm;

/**
 * @author liuhan19691
 */
public interface IMainFunction {


    /**
     * 获取功能名称
     *
     * @return
     */
    String getFunctionName();

    /**
     * 获取具体功能的窗口
     *
     * @return 具体功能的窗口
     */
    BaseForm getFunctionForm();

}
