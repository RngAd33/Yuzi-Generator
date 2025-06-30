package com.yupi.template.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 文件配置
 */
@Data
public class TemplateMakerFileConfig {

    private List<FileInfoConfig> files;

    private FileGroupConfig fileGroupConfig;

    @NoArgsConstructor
    @Data
    public static class FileInfoConfig {

        private String path;

        private String condition;

        private List<FileFilterConfig> filterConfigList;

    }

    @NoArgsConstructor
    @Data
    public static class FileGroupConfig {

        private String groupKey;

        private String groupName;

        private String condition;

    }
}