package com.fsyj.Util;

import com.fsyj.Main.Main;

import java.io.File;
import java.net.URL;
import java.util.Objects;

public class PathUtil {
    // 输出文件夹的绝对路径  末尾有分隔符
    public static String OUT_PATH;
    // 输出文件夹的相对路径  末尾有分隔符
    public static String out_dir;
    // 末尾没有分隔符
    public final static String ROOT_PATH = System.getProperty("user.dir");
    private static String rootPath = PathUtil.ROOT_PATH; // 不必去关注这个
    private static URL IMAGE_PATH = null;
    static {
        // 初始化log配置文件中的base.log
        rootPath = rootPath.replace("\\", "\\\\");
        File dir = new File(PathUtil.ROOT_PATH + File.separator + "s_out");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        OUT_PATH = dir.getAbsolutePath() + File.separator;
    }

    public static URL getImagePath() {
        return IMAGE_PATH;
    }

    public static void setImagePath(URL imagePath) {
        IMAGE_PATH = imagePath;
    }
}
