package com.fsyj.Container;

import com.fsyj.Util.SelfLogger;
import com.fsyj.Util.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimePanel extends Container {
    public TimePanel(JFrame owner) {
        /*
        休息时长 —— restTime —— restT —— reSure1
        休息间隔 —— restInterval —— restI —— reSure2
         */
        setLayout(new GridLayout(2, 3));
        JLabel restTime = new JLabel("休息时长/min");
        JLabel restInterval = new JLabel("休息间隔/min");
        // 设置居中
        restInterval.setHorizontalAlignment(JLabel.CENTER);
        restTime.setHorizontalAlignment(JLabel.CENTER);
        // 构构构构建两个文本框并设置垂直居中
        JTextField restT = new JTextField();
        JTextField restI = new JTextField();
        restI.setHorizontalAlignment(JTextField.CENTER);
        restT.setHorizontalAlignment(JTextField.CENTER);
        // 给文本域设置默认时间
        restI.setText(String.valueOf(Timer.self_timer.getIntervalAsMinutes()));
        restT.setText(String.valueOf(Timer.self_timer.getRestTimeAsMinutes()));

        // 添加两个确认按钮
        JButton reSure1 = new JButton("确认1"); // 用于休息时长
        JButton reSure2 = new JButton("确认2"); // 用于设置休息间隔
        // 为两个按钮添加单击事件——更新timer的时间
        ActionListener actionListener = e -> {
            String command = e.getActionCommand();
            // 设置时间
            if (reSure1.getText().equals(command)) { // 休息时长
                String text = restT.getText();
                if ("".equals(text)) {
                    return;
                }
                Long time = Long.valueOf(text);
                long temp = time * Timer.PRE_MINUTES;
                Timer.self_timer.setRestTime(temp);
                SelfLogger.getLogger().info("设置休息时长：" + temp);
            } else { // 休息间隔
                String text = restI.getText();
                if ("".equals(text)) {
                    return;
                }
                Long time = Long.valueOf(text);
                long temp = time * Timer.PRE_MINUTES;
                Timer.self_timer.setRestInterval(temp);
                SelfLogger.getLogger().info("设置休息间隔：" + temp);
            }
        };
        reSure1.addActionListener(actionListener);
        reSure2.addActionListener(actionListener);
        add(restTime);
        add(restT);
        add(reSure1);
        add(restInterval);
        add(restI);
        add(reSure2);
    }
}