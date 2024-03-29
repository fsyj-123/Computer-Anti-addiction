package com.fsyj.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SelfStart extends Container {
    private static boolean selfStart = false;
    public SelfStart() {
        setLayout(new GridLayout(1,3));
        add(new JLabel("设置开机自启动："));
        JButton button = new JButton(selfStart?"on":"off");
        button.addActionListener(e -> {
            selfStart = !selfStart;
            button.setText(selfStart?"on":"off");
        });
        add(button);
    }
}
