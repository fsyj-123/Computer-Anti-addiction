package com.fsyj.Frame;

import com.fsyj.Util.PathUtil;
import com.fsyj.Util.SelfClassLoader;
import com.fsyj.Util.SelfLogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;


public class LockButton extends JButton {
    ImageIcon icon;

    public LockButton(JFrame frame) {
        icon = new ImageIcon(PathUtil.getImagePath());
        setSize(200, 200);
        icon.setImage(icon.getImage().getScaledInstance(200, 200, 0));
        setIcon(icon);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelfLogger.getLogger().info("解锁按钮被点击");
                SelfClassLoader.loadUnlock(frame);
            }
        });
    }
}
