package com.fsyj.Util;


import com.fsyj.Container.StartAndEnd;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 用于获取和更新屏幕锁屏时间及锁屏间隔
 */
public class Timer {
    public static ScheduledExecutorService threadPool;
    public static Long startTime;
    public static Long stopTime;

    static {
        threadPool = Executors.newScheduledThreadPool(2);
    }

    public final static Long PRE_SECONDS = 1000L;
    public final static Long PRE_MINUTES = 60L * PRE_SECONDS;
    public static Timer self_timer = new Timer();
    // 以毫秒为单位
    private Long restTime;
    private Long restInterval;

    private Timer() {
        restTime = 300000L;
        restInterval = 2700000L;
    }

    public Long getRestTime() {
        return restTime;
    }

    public void setRestTime(Long restTime) {
        this.restTime = restTime;
    }

    public Long getRestInterval() {
        return restInterval;
    }

    public void setRestInterval(Long restInterval) {
        this.restInterval = restInterval;
    }

    public long getRestTimeAsMinutes() {
        return restTime / PRE_MINUTES;
    }

    public Long getIntervalAsMinutes() {
        return restInterval / PRE_MINUTES;
    }

    // 采用多线程的ScheduledThread来实现定时功能（若采用Timer容易造成BUG——不会将锁屏时长计算入内）
    public static void restart() {
        StackTraceElement[] trace = new Exception().getStackTrace();
        String className = trace[1].getClassName();
        String name = StartAndEnd.class.getName();
        // 如果是通过启动按钮启动程序则将程序设置为已启动状态，防止重复启动
        if (className.equals(name + "$1")) {
            StartAndEnd.isStarted = true;
        }
        Long minutes = Timer.self_timer.getIntervalAsMinutes();
        SelfLogger.getLogger().info("程序启动，休息时长为：{" +Timer.self_timer.getRestTimeAsMinutes()+"}，休息间隔为：{"+Timer.self_timer.getIntervalAsMinutes()+"}");
        // 设置程序启动时间
        threadPool.schedule(new FrameThread(), minutes, TimeUnit.MINUTES);
    }
}
