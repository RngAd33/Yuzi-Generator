package com.yupi.cli.command;

import com.yupi.model.DataModel;
import java.util.concurrent.Callable;
import lombok.Data;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "test", mixinStandardHelpOptions = true)
@Data
public class TestGroupCommand implements Runnable {

    @Option(names = {"--needGit"}, arity = "0..1", description = "是否生成 .gitignore 文件", interactive = true, echo = true)
    private boolean needGit = true;

    @Option(names = {"-l", "--loop"}, arity = "0..1", description = "是否生成循环", interactive = true, echo = true)
    private boolean loop = false;

    static DataModel.MainTemplate mainTemplate = new DataModel.MainTemplate();

    @Override
    public void run() {
        System.out.println(needGit);
        System.out.println(loop);
        if (true) {
            System.out.println("输入核心模板配置：");
            CommandLine commandLine = new CommandLine(MainTemplateCommand.class);
            commandLine.execute( "--author", "--outputText");
        }
        System.out.println(mainTemplate);
        // 需要赋值给 DataModel
        //        DataModel dataModel = new DataModel();
        //        BeanUtil.copyProperties(this, dataModel);
        //        dataModel.mainTemplate = mainTemplate;
        //        MainGenerator.doGenerate(dataModel);
    }

    @Command(name = "mainTemplate", description = "用于生成核心模板文件")
    @Data
    public static class MainTemplateCommand implements Runnable {

        /**
         * 作者注释
         */
        @Option(names = {"-a", "--author"}, arity = "0..1", description = "作者注释", interactive = true, echo = true)
        private String author = "yupi";

        /**
         * 输出信息
         */
        @Option(names = {"-o", "--outputText"}, arity = "0..1", description = "输出信息", interactive = true, echo = true)
        private String outputText = "sum = ";

        @Override
        public void run() {
            mainTemplate.author = author;
            mainTemplate.outputText = outputText;
        }
    }

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(TestGroupCommand.class);
        commandLine.execute("-l");
        //        commandLine.execute( "--help");
    }
}