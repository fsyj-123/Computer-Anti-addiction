package com.fsyj.Util;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

public class SelfClassLoader extends ClassLoader {

    // 初始化类对象
    static Object classObject;
    static Class<?> clazz;

    static {
        File f = new File(PathUtil.OUT_PATH);
        URI uri = f.toURI();
        URL url;
        try {
            url = uri.toURL();
            URL[] urls = new URL[1];
            urls[0] = url;
            URLClassLoader classLoader = new URLClassLoader(urls);
            clazz = classLoader.loadClass(UserFile.getClassName());
            classObject = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            SelfLogger.getLogger().warn(e);
        }
    }

    public static void loadUnlock(JFrame frame) {
        try {
            // 当用户单击解锁按钮后，调用用户提供的Java文件编译后的handle方法
            Object result = clazz.getDeclaredMethod("handle").invoke(classObject);
            if ((boolean) result) {
                // 先销毁窗口，然后再激活定时线程
                frame.dispose();
                FrameThread.start();
            }
        } catch (Exception ex) {
            SelfLogger.getLogger().error(ex);
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File(PathUtil.OUT_PATH + name.replace(".", File.separator) + ".class");
        FileInputStream inputStream = null;
        // 构建字节输出流
        ByteArrayOutputStream outputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(file);
            outputStream = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            data = outputStream.toByteArray();
        } catch (IOException e) {
            SelfLogger.getLogger().error(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        assert data != null;
        return defineClass(name, data, 0, data.length);
    }
}
