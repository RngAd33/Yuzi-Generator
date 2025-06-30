package com.yupi.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.yupi.generator.MainGenerator;
import com.yupi.model.MainTemplateConfig;
import freemarker.template.TemplateException;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * generate命令
 */
@Data
@Command(name = "generate", description = "生成代码", mixinStandardHelpOptions = true)
public class GenerateCommand implements Callable {

    /**
     * 是否生成循环（开关）
     */
    @Option(names = {"-l", "--loop"}, arity = "0..1", description = "是否循环？", echo = true, interactive = true)
    private boolean loop;

    /**
     * 作者注释
     */
    @Option(names = {"-a", "--author"}, arity = "0..1", description = "定义作者名称", echo = true, interactive = true)
    private String author = "RngAd33";

    /**
     * 输出信息
     */
    @Option(names = {"-o", "--output"}, arity = "0..1", description = "输出结果", echo = true, interactive = true)
    private String outputText = "结果：";

    @Override
    public Integer call() throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this, mainTemplateConfig);  // 复制属性
        System.out.println("配置信息：" + mainTemplateConfig);

        try {
            MainGenerator.doGenerate(mainTemplateConfig);  // 关键语句
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}