package ${basePackage};

import ${basePackage}.cli.CommandExecutor;

public class Main {
    public static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutor();
        // args = new String[] {"generate", "-l"};   // 测试命令
        commandExecutor.doExecute(args);
    }
}