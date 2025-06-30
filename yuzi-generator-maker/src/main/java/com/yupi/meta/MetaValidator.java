package com.yupi.meta;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.yupi.meta.enums.FileGenerateTypeEnum;
import com.yupi.meta.enums.FileTypeEnum;
import com.yupi.meta.enums.ModelTypeEnum;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * meta校验
 * 降低圈复杂度，需要减少嵌套解构，尽早结束程序
 * 尽可能避免硬编码和魔法值，使用常量枚举类可以更加灵活规范
 */
public class MetaValidator {

    /**
     * 校验入口
     *
     * @param meta
     */
    public static void doValidAndFill(Meta meta) {
        validAndFillMetaRoot(meta);
        validAndFillFileConfig(meta);
        validAndFillModelConfig(meta);
    }

    /**
     * FileConfig 校验
     *
     * @param meta
     */
    private static void validAndFillFileConfig(Meta meta) {
        Meta.FileConfig fileConfig = meta.getFileConfig();
        if (fileConfig == null) {
            return;
        }
        // sourceRootPath（必填）
        String sourceRootPath = fileConfig.getSourceRootPath();
        if (StrUtil.isBlank(sourceRootPath)) {
            throw new MetaException("——！未填写 sourceRootPath！——");
        }

        // inputRootPath（.source + sourceRootPath 的最后一个层级路径）
        String inputRootPath = fileConfig.getInputRootPath();
        String defaultInputPath = ".source/" + FileUtil.getLastPathEle(Paths.get(sourceRootPath)).getFileName().toString();
        if (StrUtil.isBlank(inputRootPath)) {
            fileConfig.setInputRootPath(defaultInputPath);
        }

        // outputPath（默认为当前路径下的 generated）
        String outputRootPath = fileConfig.getOutputRootPath();
        String defaultOutputPath = "generated";
        if (StrUtil.isEmpty(outputRootPath)) {
            fileConfig.setOutputRootPath(defaultOutputPath);
        }

        // fileConfigType（默认为 dir）
        String fileConfigType = fileConfig.getType();
        String defaultType = FileTypeEnum.DIR.getValue();
        if (StrUtil.isEmpty(fileConfigType)) {
            fileConfig.setType(defaultType);
        }

        // fileInfo 默认值
        List<Meta.FileConfig.FileInfo> fileInfoList = fileConfig.getFiles();
        if (!CollectionUtil.isNotEmpty(fileInfoList)) {
            return;
        }
        for (Meta.FileConfig.FileInfo fileInfo : fileInfoList) {
            // 不对文件组group校验
            String groupKey = fileInfo.getGroupKey();
            if (StrUtil.isNotEmpty(groupKey)) {
                continue;
            }

            // -inputPath（必填）
            String inputPath = fileInfo.getInputPath();
            if (StrUtil.isBlank(inputPath)) {
                throw new MetaException("——！未填写 inputPath！——");
            }

            // -outputPath（默认等于inputPath）
            String outputPath = fileInfo.getOutputPath();
            if (StrUtil.isEmpty(outputPath)) {
                fileInfo.setOutputPath(inputPath);
            }

            // -type（默认 inputPath 有文件后缀为 file，否则为 dir）
            String type = fileInfo.getType();
            if (StrUtil.isBlank(type)) {
                if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))) {
                    fileInfo.setType(FileTypeEnum.DIR.getValue());   // 元文件后缀
                } else {
                    fileInfo.setType(FileTypeEnum.FILE.getValue());   // 文件后缀
                }
            }

            // generateType（如果文件末尾扩展名不为 ftl，generateType 默认为 static，否则为 dynamic）
            String generateType = fileInfo.getGenerateType();
            if (StrUtil.isEmpty(generateType)) {
                if (inputPath.endsWith(".ftl")) {
                    fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());   // 动态模板
                } else {
                    fileInfo.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());   // 静态模板
                }
            }
        }
    }

    /**
     * modelConfig 校验
     *
     * @param meta
     */
    private static void validAndFillModelConfig(Meta meta) {
        Meta.ModelConfig modelConfig = meta.getModelConfig();
        if (modelConfig == null) {
            return;
        }
        // ModelConfig 默认值
        List<Meta.ModelConfig.ModelInfo> modelInfoList = modelConfig.getModels();
        if (!CollectionUtil.isNotEmpty(modelInfoList)) {
            return;
        }
        for (Meta.ModelConfig.ModelInfo modelInfo : modelInfoList) {
            // 不对模型组group校验
            String groupKey = modelInfo.getGroupKey();
            if (StrUtil.isNotEmpty(groupKey)) {
                // 生成中间参数 "--author" and "--outputText"
                List<Meta.ModelConfig.ModelInfo> subModelInfoList = modelInfo.getModels();
                String allArgsStr = modelInfo.getModels().stream()
                        .map(subModelInfo -> String.format("\"--%s\"", subModelInfo.getFieldName()))
                        .collect(Collectors.joining(", "));
                modelInfo.setAllArgsStr(allArgsStr);
                continue;
            }

            // fieldName（必填）
            String fieldName = modelInfo.getFieldName();
            if (StrUtil.isBlank(fieldName)) {
                throw new MetaException("——！未填写 fieldName！——");
            }

            // 模型配置
            String modelInfoType = modelInfo.getType();
            if (StrUtil.isEmpty(modelInfoType)) {
                modelInfo.setType(ModelTypeEnum.STRING.getValue());
            }
        }
    }

    /**
     * 基础信息校验
     *
     * @param meta
     */
    private static void validAndFillMetaRoot(Meta meta) {
        String name = StrUtil.blankToDefault(meta.getName(), "acm-template-pro-generator");
        String description = StrUtil.emptyToDefault(meta.getDescription(), "ACM 示例模板生成器");
        String basePackage = StrUtil.blankToDefault(meta.getBasePackage(), "com.yupi");
        String version = StrUtil.emptyToDefault(meta.getVersion(), "1.0");
        String author = StrUtil.emptyToDefault(meta.getAuthor(), "RngAd33");
        String createTime = StrUtil.emptyToDefault(meta.getCreateTime(), "2024-02-24");
        meta.setName(name);
        meta.setDescription(description);
        meta.setBasePackage(basePackage);
        meta.setVersion(version);
        meta.setAuthor(author);
        meta.setCreateTime(createTime);
    }
    /* 以上为简化版本
    private static void validAndFillMetaRoot(Meta meta) {
        String name = meta.getName();
        if (StrUtil.isBlank(name)) {
            name = "acm-template-pro-generator";
            meta.setName(name);
        }

        String description = meta.getDescription();
        if (StrUtil.isEmpty(description)) {
            description = "ACM 示例模板生成器";
            meta.setDescription(description);
        }

        String basePackage = meta.getBasePackage();
        if (StrUtil.isBlank(basePackage)){
            basePackage = "com.yupi";
            meta.setBasePackage(basePackage);
        }

        String version = meta.getVersion();
        if (StrUtil.isEmpty(version)) {
            version = "1.0";
            meta.setVersion(version);
        }

        String author = meta.getAuthor();
        if (StrUtil.isEmpty(author)) {
            author = "RngAd33";
            meta.setAuthor(author);
        }

        String createTime = meta.getCreateTime();
        if (StrUtil.isEmpty(createTime)) {
            createTime = "2024-02-24";
            meta.setCreateTime(createTime);
        }
    }
    */
}