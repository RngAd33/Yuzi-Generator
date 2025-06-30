package com.yupi.template;

import cn.hutool.core.util.StrUtil;
import com.yupi.meta.Meta;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 模板制作自定义工具类
 */
public class TemplateMakerUtils {

    /**
     * 从未分组文件中移除组内的同名文件
     *
     * @param fileInfoList 文件列表
     * @return newFileInfoList
     */
    public static List<Meta.FileConfig.FileInfo> removeGroupFileFromRoot(List<Meta.FileConfig.FileInfo> fileInfoList) {
        // 获取到所有分组
        List<Meta.FileConfig.FileInfo> groupFileInfoList = fileInfoList.stream()
                .filter(fileInfo -> StrUtil.isNotBlank(fileInfo.getGroupKey()))   // groupKey不为空就收集到groupFileInfoList中
                .collect(Collectors.toList());

        // 获取所有分组内的文件列表
        List<Meta.FileConfig.FileInfo> groupInnerFileInfoList = groupFileInfoList.stream()
                .flatMap(fileInfo -> fileInfo.getFiles().stream())   // 返回嵌套的文件列表groupInnerFileInfoList
                .collect(Collectors.toList());

        // 获取所有分组内的文件输入路径集合
        /* 使用集合更容易去重 */
        Set<String> fileInputPathSet = groupInnerFileInfoList.stream()
                .map(Meta.FileConfig.FileInfo::getInputPath)
                .collect(Collectors.toSet());

        // 移除所有名称在集合中的外层文件
        return fileInfoList.stream()
                .filter(fileInfo -> !fileInputPathSet.contains(fileInfo.getInputPath()))   // 不在集合内才保留
                .collect(Collectors.toList());
    }

}