package com.yupi.template.model;

import lombok.Data;

/**
 * 输出配置
 */
@Data
public class TemplateMakerOutputConfig {

    // 从未分组文件中移除组内的同名文件
    private boolean removeGroupFileFromRoot = true;

}