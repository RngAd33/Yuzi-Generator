package com.yupi.template;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import com.yupi.template.enums.FileFilterRangeEnum;
import com.yupi.template.enums.FileFilterRuleEnum;
import com.yupi.template.model.FileFilterConfig;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件过滤器
 */
public class FileFilter {

    /**
     * 对某个文件或目录进行过滤
     *
     * @param filePath 文件 / 目录路径
     * @param fileFilterConfigList 过滤规则
     * @return 文件列表
     */
    public static List<File> doFilter(String filePath, List<FileFilterConfig> fileFilterConfigList) {
        List<File> fileList = FileUtil.loopFiles(filePath);
        return fileList.stream()
                .filter(file -> doSingleFileFilter(fileFilterConfigList, file))
                .collect(Collectors.toList());
    }

    /**
     * 单个文件过滤
     *
     * @param fileFilterConfigList 过滤规则
     * @param file 单个文件
     * @return 判断是否保留
     */
    public static boolean doSingleFileFilter(List<FileFilterConfig> fileFilterConfigList, File file) {
        String fileName = file.getName();
        String fileContent = FileUtil.readUtf8String(file);
        boolean result = true;

        if (CollUtil.isEmpty(fileFilterConfigList)) {
            return true;
        }

        for (FileFilterConfig fileFilterConfig : fileFilterConfigList) {
            String range = fileFilterConfig.getRange();
            String rule = fileFilterConfig.getRule();
            String value = fileFilterConfig.getValue();

            FileFilterRangeEnum fileFilterRangeEnum = FileFilterRangeEnum.getEnumByValue(range);
            if (fileFilterRangeEnum == null) {
                continue;
            }

            String content = fileName;
            switch (fileFilterRangeEnum) {
                case FILE_NAME:
                    content = fileName; break;
                case FILE_CONTENT:
                    content = fileContent; break;
                default:
            }

            FileFilterRuleEnum fileFilterRuleEnum = FileFilterRuleEnum.getEnumByValue(rule);
            if (fileFilterRuleEnum == null) {
                continue;
            }
            switch (fileFilterRuleEnum) {
                case CONTAINS:
                    result = content.contains(value); break;
                case STARTS_WITH:
                    result = content.startsWith(value); break;
                case ENDS_WITH:
                    result = content.endsWith(value); break;
                case REGEX:
                    result = content.matches(value); break;
                case EQUALS:
                    result = content.equals(value); break;
                default:
            }
        }
        return result;
    }
}