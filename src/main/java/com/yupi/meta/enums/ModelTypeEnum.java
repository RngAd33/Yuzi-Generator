package com.yupi.meta.enums;

import lombok.Getter;

/**
 * 模型类型枚举
 */
@Getter
public enum ModelTypeEnum {

    STRING("字符串", "string"),
    BOOLEAN("布尔类型", "boolean"),
    MAINTEMPLATE("模型组", "MainTemplate");

    private final String text;

    private final String value;

    ModelTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

}