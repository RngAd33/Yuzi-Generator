package com.yupi.template.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 模型配置
 */
@Data
public class TemplateMakerModelConfig {

    private List<ModelInfoConfig> models;

    private ModelGroupConfig modelGroupConfig;

    @NoArgsConstructor
    @Data
    public static class ModelInfoConfig {

        private String abbr;

        private String fieldName;

        private String type;

        private String description;

        private Object defaultValue;

        private String replaceText;   // 要替换的目标文本

    }

    @Data
    public static class ModelGroupConfig {

        private String groupKey;

        private String groupName;

        private String condition;

        private String type;

        private String description;

    }
}