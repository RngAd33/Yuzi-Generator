package com.yupi.generator.file;

import com.yupi.model.DataModel;
import com.yupi.model.DataModel.MainTemplate;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.IOException;

/**
 * 动静结合-生成完整代码
 */
public class MainFileGenerator {

    /**
     * 生成文件
     *
     * @param model 模型文件
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(Object model) throws IOException, TemplateException {
        // - 获取整个项目的根路径
        String projectPath = System.getProperty("user.dir");
        File parentFile = new File(projectPath).getParentFile();

        // - 输入路径：ACM 示例代码模板目录
        String inputPath = new File(parentFile, "yuzi-generator-demo-projects/acm-template-pro").getAbsolutePath();

        // 复制，生成静态文件
        /* 直接输出到项目的根目录 */
        StaticFileGenerator.copyFilesByHutool(inputPath, projectPath);

        // 动态生成文件
        /* 此处路径已写死，需要设置元信息meta优化 */
        String dynamicInputPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String dynamicOutputPath = projectPath + File.separator + "acm-template/src/com/yupi/acm/MainTemplate.java";
        DynamicFileGenerator.doGenerate(dynamicInputPath, dynamicOutputPath, model);
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
        DataModel dataModel = new DataModel();
        MainTemplate mainTemplate = new MainTemplate();
        dataModel.setLoop(false);
        mainTemplate.setAuthor("RngAd33");
        mainTemplate.setOutputText("输出结果");
        doGenerate(dataModel);
    }
}