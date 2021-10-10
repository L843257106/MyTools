package pers.liuhan.toolkit;


import pers.liuhan.toolkit.factory.FunctionFactory;
import pers.liuhan.toolkit.interfaces.IMainFunction;
import pers.liuhan.toolkit.util.InputUtil;

/**
 * @author liuhan19691
 */
public class ToolkitMain {

    public static void main(String[] args) {
        String command;
        IMainFunction function;
        while (true) {
            System.out.println("Please choose a function: ");
            command = InputUtil.inputLienString();
            if ("0".equals(command)) {
                break;
            }
            function = FunctionFactory.getMainFunctionByFunctionNo(command);
            if (function == null) {
                continue;
            }
            function.run();
        }
        InputUtil.closeInput();
    }


}
