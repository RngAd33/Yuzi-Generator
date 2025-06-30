package com.yupi;

import com.yupi.cli.CommandExecutor;

/**
 * 全局调用入口
 */
public class Main {
    public static void main(String[] args) {
        args = new String[]{"generate", "-l", "true", "-a", "RngAd33", "-o", "23333"};  // 测试命令
        // args = new String[] {"--help"};  // 测试命令
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}