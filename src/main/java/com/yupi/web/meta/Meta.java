package com.yupi.web.meta;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 元数据成员变量
 */
@NoArgsConstructor
@Data
public class Meta {

    private String name;
    private String description;
    private String basePackage;
    private String version;
    private String author;
    private String createTime;
    private FileConfig fileConfig;
    private ModelConfig modelConfig;

    @NoArgsConstructor
    @Data
    public static class FileConfig {
        private String inputRootPath;
        private String outputRootPath;
        private String sourceRootPath;
        private String type;
        private List<FileInfo> files;

        @NoArgsConstructor
        @Data
        public static class FileInfo {
            private String inputPath;
            private String outputPath;
            private String groupKey;
            private String groupName;
            private String type;
            private String condition;
            private String generateType;
            private List<FileInfo> files;
        }
    }

    @NoArgsConstructor
    @Data
    public static class ModelConfig {
        private List<ModelInfo> models;

        @NoArgsConstructor
        @Data
        public static class ModelInfo {
            private String abbr;
            private String fieldName;
            private String groupKey;
            private String groupName;
            private String type;
            private String description;
            private Object defaultValue;   // 默认值，使用Object类型避免类型冲突问题
            private List<ModelInfo> models;
            private String condition;

            private String allArgsStr;   // 中间参数，用于记录该分组下拼接的字符串
        }
    }
}