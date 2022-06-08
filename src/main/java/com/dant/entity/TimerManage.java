package com.dant.entity;

public class TimerManage {
    /*  Gère le temps d'execution du programme */
    private static long start_timer;
    private static long stop_timer;

    // Démarre le timer
    public static void start(){
        stop_timer=0;
        start_timer=System.currentTimeMillis();
    }

    // Stop le timer
    public static void pause(){
        if(start_timer==0) return;
        stop_timer= System.currentTimeMillis();
    }
    public static long getTime(){
        return stop_timer - start_timer;
    }
}
