package com.yupi.generator.main;

import freemarker.template.TemplateException;
import java.io.IOException;
import java.lang.InterruptedException;

/**
 * 调用父类，生成文件
 */
public class MainGenerator extends GenerateTemplate {

    /**
     * 生成精简版程序
     *
     * @param outputPath
     * @param jarPath
     * @param shellOutputFilePath
     * @param sourceCopyDestPath
     */
    @Override
    protected void buildDist(String outputPath, String jarPath, String shellOutputFilePath, String sourceCopyDestPath) {
        System.out.println("——精简版程序不再包含dist部分——");
    }

    /**
     * 测试方法
     *
     * @param args
     * @throws IOException
     * @throws TemplateException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, TemplateException, InterruptedException {
        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.doGenerate();   // 继承了GenerateTemplate.doGenerate()方法
    }
}