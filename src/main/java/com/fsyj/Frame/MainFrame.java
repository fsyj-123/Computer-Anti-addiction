package com.fsyj.Frame;

import com.fsyj.Container.FileChooser;
import com.fsyj.Container.StartAndEnd;
import com.fsyj.Container.TimePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException {
        setSize(980,450);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        // 设置相对于屏幕居中显示
        setLocationRelativeTo(getOwner());
        setResizable(false);
        setLayout(new BorderLayout());
        add(new StartAndEnd(),BorderLayout.SOUTH);
        add(new FileChooser(),BorderLayout.CENTER);
        add(new TimePanel(this),BorderLayout.NORTH);
        setVisible(true);
    }
}
