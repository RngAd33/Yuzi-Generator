package com.yupi.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.yupi.model.MainTemplateConfig;
import picocli.CommandLine.Command;

import java.lang.reflect.Field;

/**
 * config命令
 */
@Command(name = "config", description = "查看参数配置信息", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable {

    @Override
    public void run() {

        // Hutool反射工具类
        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);

        // 遍历、打印每个字段的信息
        for (Field field : fields) {
            System.out.println("字段名称：" + field.getName());
            System.out.println("字段类型：" + field.getType());
            System.out.println("--------------------------");
        }
    }
}