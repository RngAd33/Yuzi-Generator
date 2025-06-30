package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 文件生成
 */
public class MainGenerator {

    /**
     * 动静结合，生成完整代码
     *
     * @param model
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(Object model) throws IOException, TemplateException {
        /* 生成动态文件 */
        // - 生成目录
        String inputRootPath = "E:/document/资料/计算机/A小金库/Java/yuzi-generator/yuzi-generator-demo-projects/acm-template-pro";
        String outputRootPath = "E:/document/资料/计算机/A小金库/Java/yuzi-generator/yuzi-generator-basic/acm-template-pro";
        // - 模板填坑
        String inputPath = new File(inputRootPath, "src/com/yupi/acm/MainTemplate.java.ftl").getAbsolutePath();
        String outputPath = new File(outputRootPath, "src/com/yupi/acm/MainTemplate.java.").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        /* 生成静态文件 */
        // - .gitignore
        inputPath = new File(inputRootPath, ".gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath, ".gitignore").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);
        // - README.md
        inputPath = new File(inputRootPath, "README.md").getAbsolutePath();
        outputPath = new File(outputRootPath, "README.md").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);
    }

    /**
     * 测试方法
     *
     * @param args
     * @throws TemplateException
     * @throws IOException
     */
    public static void main(String[] args) throws TemplateException, IOException {
        // 生成数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setAuthor("RngAd33");
        mainTemplateConfig.setOutputText("输出结果");

        doGenerate(mainTemplateConfig);
    }
}