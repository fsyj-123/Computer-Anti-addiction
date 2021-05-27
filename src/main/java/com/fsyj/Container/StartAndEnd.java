package com.fsyj.Container;

import com.fsyj.Util.SelfLogger;
import com.fsyj.Util.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartAndEnd extends Container {
    public static boolean isStarted = false;
    public StartAndEnd() {
        JButton start = new JButton("启动程序");
        JButton end = new JButton("结束程序");
        JLabel label = new JLabel();
        start.addActionListener(e -> {
            if (isStarted) {
                System.out.println("yes");
                label.setText("程序已启动，请勿重复启动");
                return;
            }
            // 添加程序启动提醒
            label.setText("程序已启动");
            Timer.restart();
        });
        end.addActionListener(e -> {
            SelfLogger.getLogger().info("程序关闭");
            System.exit(1);
        });
        // 增加暂停功能
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
        add(start);
        add(end);
        add(label);
        add(new SelfStart());
    }
}
