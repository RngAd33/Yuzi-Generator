import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkerTest {

    @Test
    public void test() throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        // 创建模板对象，加载指定模板
        Template template = configuration.getTemplate("myweb.html.ftl");

        // 创建数据模型
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("currentYear", 2025);
        List<Map<String, Object>> menuItems = new ArrayList<>();
        Map<String, Object> menuItem1 = new HashMap<>();
        menuItem1.put("url", "https://codefather.cn");
        menuItem1.put("label", "编程导航");
        Map<String, Object> menuItem2 = new HashMap<>();
        menuItem2.put("url", "https://laoyujianli.com");
        menuItem2.put("label", "老鱼简历");
        Map<String, Object> menuItem3 = new HashMap<>();
        menuItem3.put("url", "https://www.mianshiya.com");
        menuItem3.put("label", "面试鸭");
        Map<String, Object> menuItem4 = new HashMap<>();
        menuItem4.put("url", "https://rngad33.github.io/blog233.github.io");
        menuItem4.put("label", "笔记博客");
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        menuItems.add(menuItem3);
        menuItems.add(menuItem4);
        dataModel.put("menuItems", menuItems);

        // 指定生成的文件
        Writer out = new FileWriter("myweb.html");

        // 生成文件
        template.process(dataModel, out);

        // 生成文件后别忘了关闭哦
        out.close();
    }
}