# ${name}

> ${description}
>
> 作者：${author}
>
> 基于 程序员鱼皮的 [鱼籽代码生成器项目](https://github.com/liyupi/yuzi-generator) 制作，感谢您的使用！

可以通过命令行交互式输入的方式动态生成想要的项目代码

## 使用说明

执行项目根目录下的脚本文件：
```
generator <命令> <选项参数>
```

示例命令：
```
generator generate <#list modelConfig.models as modelInfo>-${modelInfo.abbr!"default"}</#list>
```

## 参数说明
<#list modelConfig.models as modelInfo>
${modelInfo?index + 1}）${modelInfo.fieldName!"默认字段名"}

类型：${modelInfo.type!"未知类型"}

描述：${modelInfo.description!"无描述"}

默认值：${(modelInfo.defaultValue?string)!"无默认值"}

缩写：-${modelInfo.abbr!"default"}

</#list>