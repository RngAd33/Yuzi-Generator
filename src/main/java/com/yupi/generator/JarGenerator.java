package com.yupi.generator;

import java.io.*;

/**
 * Jar生成
 */
public class JarGenerator {

    /**
     * 打包
     *
     * @param projectDir
     * @throws IOException
     * @throws InterruptedException
     */
    public static void doGenerate(String projectDir) throws IOException, InterruptedException {

        System.out.println("正在打包……");
        // 清理以前的构建并打包（请注意：不同操作系统执行的命令不同）
        String winMavenCommand = "mvn.cmd clean package -DskipTests=true";
        String otherMavenCommand = "mvn clean package -DskipTests=true";

        ProcessBuilder processBuilder = new ProcessBuilder(winMavenCommand.split(" "));   // 必须用空格拆分命令
        processBuilder.directory(new File(projectDir));   // 指定路径

        Process process = processBuilder.start();

        // 读取命令的输出
        InputStream inputStream = process.getInputStream();   // 获取输入流
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));   // 缓冲区读取器
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // 等待命令执行完成并返回状态
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            System.out.println("————！！！错误发生，打包进程中断，请尽快排查错误！！！————");
        } else {
            System.out.println("————jar打包完成———>>>");
        }
    }

    /**
     * 测试方法
     *
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        doGenerate("E:/document/资料/计算机/A小金库/Java/yuzi-generator/yuzi-generator-maker/generated");
    }
}