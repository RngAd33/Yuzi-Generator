package com.yupi;

import com.yupi.generator.main.MainGenerator;
import freemarker.template.TemplateException;
import java.io.IOException;

/**
 * 全局调用入口
 */
public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.doGenerate();
    }
}