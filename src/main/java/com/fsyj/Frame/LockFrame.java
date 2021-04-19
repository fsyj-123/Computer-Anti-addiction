package com.fsyj.Frame;

import javax.swing.*;
import java.awt.*;

public class LockFrame extends JFrame {
    public LockFrame() throws HeadlessException {
        setAlwaysOnTop(true);
        // 获取屏幕尺寸
        Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);
        // 设置相对于屏幕居中显示
        setLocationRelativeTo(getOwner());
        setResizable(false);
        // 设置窗口无边框
        setUndecorated(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        LockButton lockButton = new LockButton(this);
        panel.add(lockButton);
        panel.setBackground(Color.BLACK);
        add(panel);
        setBackground(Color.BLACK);
        setVisible(true);
    }
}
