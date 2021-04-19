package com.fsyj.Util;

import com.fsyj.Frame.LockFrame;
import com.fsyj.Util.Timer;

import java.util.concurrent.TimeUnit;

public class FrameThread implements Runnable {
    static boolean isLocked = false;
    public final static String CLASS_NAME = "com.fsyj.Util.FrameThread";

    // 启用锁屏窗口，用sleep来实现定时长锁屏
    @Override
    public void run() {
        LockFrame lockFrame = new LockFrame();
        try {
            Long time = Timer.self_timer.getRestTimeAsMinutes();
            TimeUnit.MINUTES.sleep(time);
            lockFrame.dispose();
            start();
        } catch (InterruptedException e) {
            SelfLogger.getLogger().warn(e);
        }
    }

    /*
    应该在这个类中加一个阻断节点，即如果按钮已经单击解锁后，就不应该再调用这个方法。
    这个方法用于统一调度重启锁屏界面
     */
    public static void start() {
        StackTraceElement[] trace = new Exception().getStackTrace();
        String className = trace[1].getClassName();
        /*
         当其它方法调用这个方法时设置isLocked为true，然后本类调用这个方法时就直接把isLocked设置为false
         当第二次本类方法调用这个方法且为被其他方法调用过就直接重新调用
         */
        SelfLogger.getLogger().info("{" + className + "} 调用此方法，islocked = {" + isLocked + "}");
        if (CLASS_NAME.equals(className) && isLocked) {
            isLocked = false;
        } else if (CLASS_NAME.equals(className) && !isLocked) {
            Timer.restart();
        } else {
            Timer.restart();
            isLocked = true;
        }
    }
}