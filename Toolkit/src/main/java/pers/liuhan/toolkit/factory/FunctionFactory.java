package pers.liuhan.toolkit.factory;


import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.liuhan.toolkit.interfaces.IMainFunction;

import java.util.Map;

/**
 * @author liuhan19691
 */
public class FunctionFactory {

    private static ApplicationContext springBeanContext;

    static {
        try {
            springBeanContext = new ClassPathXmlApplicationContext("SpringContext.xml");
            if (springBeanContext == null) {
                System.out.println("Initialization SpringIoc Failed!");
            }
        } catch (Exception e) {
            System.out.println("Initialization SpringIoc Failed!" + e.getCause());
            throw e;
        }

    }

    public static IMainFunction getMainFunctionByFunctionNo(String funcNo) {
        IMainFunction tarFunction = null;
        try {
            tarFunction = springBeanContext.getBean(funcNo, IMainFunction.class);
        } catch (NoSuchBeanDefinitionException e) {
            System.out.println("Get Function [" + funcNo + "] Failed!");
        }
        return tarFunction;
    }

    public static Map<String, IMainFunction> getBeansMap() {
        return springBeanContext.getBeansOfType(IMainFunction.class);
    }
}
