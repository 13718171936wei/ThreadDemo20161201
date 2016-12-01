package com.example;

import java.util.concurrent.TimeUnit;

import jdk.nashorn.internal.ir.Block;

/**
 * Created by 行走的力量 on 2016/12/1.
 */


/**
 * 案例：用案例解释线程的六种运行状态,其中Pig类实现Runnable接口，逻辑是打印当前运行的线程信息，
 * 每隔一秒打印一次。在Main方法中启动十个Pig线程设置相应的线程优先级别，并且将初始的线程状态
 * 保存到线程状态数组中，在运行的过程判断当前线程状态和初始状态是否相同，如果不同则打印当前线
 * 程的信息保存到日志文件中。
 */
public class Pig implements Runnable {


    @Override
    public void run() {
        for (int i=0;i<10;i++){
            try {
                //让线程睡一秒
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //打印当前执行线程信息
            System.out.println("ThreadName:"+Thread.currentThread().getName());
        }
    }
}

