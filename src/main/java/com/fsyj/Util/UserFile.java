package com.fsyj.Util;

import java.io.*;
import java.util.HashMap;

public class UserFile {
    public static UserFile modelFile = new UserFile(null, null);
    // 用于存放类文件的类名
    public final static String CLASS_NAME = "CLASS_NAME";
    private File musicFile;
    private File classFile;
    static HashMap<String, String> map;

    private UserFile(File musicFile, File classFile) {
        this.musicFile = musicFile;
        this.classFile = classFile;
        map = new HashMap<>();
    }

    public File getMusicFile() {
        return musicFile;
    }

    public void setMusicFile(File musicFile) {
        this.musicFile = musicFile;
    }

    public File getClassFile() {
        return classFile;
    }

    // 利用HashMap设置类文件的文件名
    public void setClassFile(File classFile) {
        this.classFile = classFile;
        String[] split = classFile.getName().split(".java");
        if (map.containsKey(CLASS_NAME)) {
            map.remove(CLASS_NAME);
        } else {
            map.put(CLASS_NAME, split[0]);
        }
    }
    public static String getClassName() {
        if (!map.containsKey(CLASS_NAME)) {
            throw new RuntimeException("类文件还未设置");
        }
        return map.get(CLASS_NAME);
    }
}
