package com.dant.entity;

public class TimerManage {
    private static long start_timer;
    private static long stop_timer;

    public static void start(){
        stop_timer=0;
        start_timer=System.currentTimeMillis();
    }
    public static void pause(){
        if(start_timer==0) return;
        stop_timer= System.currentTimeMillis();
    }
    public static long getTime(){
        return stop_timer - start_timer;
    }
}
