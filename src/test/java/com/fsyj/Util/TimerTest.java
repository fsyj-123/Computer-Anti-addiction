package com.fsyj.Util;

import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    public static void main(String[] args) {
        Timer timer = new Timer();
        System.out.println("hello");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end");
            }
        }, new Date(System.currentTimeMillis() + 1000),1);
    }
}

