package com.fsyj.Container;

import com.fsyj.Util.SelfCompiler;
import com.fsyj.Util.SelfLogger;
import com.fsyj.Util.UnlockModel;
import com.fsyj.Util.UserFile;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

public class FileChooser extends Container {
    private File classFile;
    private File musicFile;
    // 默认为朴素模式
    private Class<?> unlockModel = UnlockModel.PLAIN_MODEL;
    public FileChooser() {
        JButton musicOpen = new JButton("选择锁屏音乐文件");
        JComboBox<String> box = new JComboBox<>();
        box.addItem("--选择锁屏模式--");
        box.addItem("朴素模式");
        JButton musicFileSure = new JButton("确认");
        JButton unlockModelButton = new JButton("确认"); // 模式选择按钮
        musicOpen.setSize(10,30);
        // 添加模式选择响应事件
        unlockModelButton.addActionListener(e -> new Thread(() -> {
            int selectedIndex = box.getSelectedIndex();
            if (selectedIndex == 0) {
                JOptionPane.showMessageDialog(null,"error","请选择锁屏模式",JOptionPane.ERROR_MESSAGE);
            } else if (selectedIndex == 1) {
                unlockModel = UnlockModel.PLAIN_MODEL;
            }
//                        取消自定义模式
//                        else {
//                            // 自定义模式
//                            unlockModel = selfDefineModel();
//                        }
            SelfLogger.getLogger().info("选取模式" + selectedIndex + "选择的类" + unlockModel);
        }).start());
        musicOpen.addActionListener(e -> {
            JFileChooser musicFile = new JFileChooser();
            musicFile.setName("选择音乐文件");
            // 设置文件的可选择类型
            musicFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            musicFile.showDialog(new JLabel(), "选择");
            // 设置文件过滤器
            musicFile.addChoosableFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    boolean accept = false;
                    if (f.getName().contains(".mp3")) {
                        accept = true;
                    }
                    return accept;
                }

                @Override
                public String getDescription() {
                    return null;
                }
            });
            UserFile.modelFile.setMusicFile(musicFile.getSelectedFile());
        });

        setLayout(new GridLayout(2, 2));
        add(musicOpen);
        add(musicFileSure);
        add(box);
        add(unlockModelButton);
    }

    // 返回自定义文件的类对象
    private Class<?> selfDefineModel() {
        JFileChooser fileChooser = new JFileChooser("请选择锁屏文件");
        // 设置文件选择模式
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.showDialog(new JLabel(), "选择");
        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().contains(".java");
            }

            @Override
            public String getDescription() {
                return null;
            }
        });
        // 获取用户选择的文件
        classFile = fileChooser.getSelectedFile();
        Class<?> object = SelfCompiler.getObject(classFile);
        SelfLogger.getLogger().info("选取Java文件" + classFile);
        return object;
    }
}