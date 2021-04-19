package com.fsyj.Util;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SelfCompiler {


    /**
     * 返回文件的名字 如 Test.txt 返回Test
     *
     * @param file   需要处理的文件
     * @param suffix 文件的格式 如 .txt
     * @return 如 Test
     */
    public static String getFileName(File file, String suffix) {
        String name = file.getName();
        return name.split(suffix)[0];
    }

    /**
     * 用于编译配置文件中所给出的Java文件
     *
     * @throws IOException
     */
    public static void Compiler(File f) throws IOException {
//        System.out.println(System.getProperty("java.class.path"));
        // 需要被编译的文件

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, StandardCharsets.UTF_8);
        Iterable<? extends JavaFileObject> objects = fileManager.getJavaFileObjects(f);
        // 设置编译后文件的输出路径
        Iterable<String> options = Arrays.asList("-d", PathUtil.OUT_PATH);
        // 编译文件
        Boolean call = compiler.getTask(null, fileManager, null, options, null, objects).call();
        SelfLogger.getLogger().info("Java文件编译{" + call + "}");
    }

    public synchronized static Class<?> getObject(File javaFile) {
        Class<?> aClass = null;
        if (!javaFile.exists()) {
            throw new RuntimeException("err file");
        }
        String name = getFileName(javaFile, ".java");
        // 如果类文件已经编译过了则不再编译
        File file = new File(PathUtil.OUT_PATH + name + ".class");
        if (!file.exists()) {
            try {
                Compiler(javaFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        SelfClassLoader classLoader = new SelfClassLoader();
        try {
            aClass = classLoader.loadClass(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return aClass;
    }
}