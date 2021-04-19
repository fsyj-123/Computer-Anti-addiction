package com.fsyj.Main;

import com.fsyj.Frame.MainFrame;
import com.fsyj.Util.PathUtil;
import com.fsyj.Util.SelfCompiler;
import com.fsyj.Util.SelfLogger;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

public class Main {
    public synchronized static void main(String[] args) {
        URL resource = Main.class.getClassLoader().getResource("Lock.png");
        PathUtil.setImagePath(resource);
        System.setProperty("log.base", PathUtil.ROOT_PATH);
        SelfLogger.getLogger().info("总程序启动");
        new MainFrame();
    }


    private static String repaire(String path) {
        System.out.println(path);
        String result = "";
        if (path.contains("file:")) {
            result = path.split("file:/")[1];
        }
        result = result.replace("!", "");
        System.out.println(result);
        return result;
    }
}