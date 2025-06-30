package ${basePackage}.cli.command;

import cn.hutool.core.io.FileUtil;
import java.io.File;
import java.util.List;
import picocli.CommandLine.Command;

@Command(name = "list", description = "查看文件列表", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {

    public void run() {
        // 输入路径
        String inputPath = "${fileConfig.inputRootPath}";
        List<File> files = FileUtil.loopFiles(inputPath);

        for (File file : files) {
            System.out.println(file);
        }
    }
}