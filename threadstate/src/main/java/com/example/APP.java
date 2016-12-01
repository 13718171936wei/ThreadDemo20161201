package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 行走的力量 on 2016/12/1.
 */

public class APP {
    public static void main(String[] args) throws IOException {

        //创建线程数组
        Thread[] taskArr = new Thread[10];

        //线程状态数组
        Thread.State[] threadStates = new Thread.State[10];

        //设置线程的状态
        for (int i = 0; i < 10; i++) {
            taskArr[i] = new Thread(new Pig());

            //分别设置状态
            if ((i % 3) == 0) {
                taskArr[i].setPriority(Thread.NORM_PRIORITY);
            } else if ((i % 3) == 1) {
                taskArr[i].setPriority(Thread.MIN_PRIORITY);
            } else if ((i % 3) == 2) {
                taskArr[i].setPriority(Thread.MAX_PRIORITY);
            }
        }

        //将线程新写入到文件中便于分析
//        FileWriter fileWriter = new FileWriter(".log.txt");
        File file = new File(".log.txt");
        PrintWriter printWriter;
        printWriter = new PrintWriter(file, "gbk");

        //循环遍历获取线程的信息
        for (int i = 0; i < 10; i++) {
            printWriter.println("线程" + i + "状态：" + taskArr[i].getState());

            //将当前线程状态保存到状态数组中
            threadStates[i] = taskArr[i].getState();
        }

        //启动线程
        for (int i = 0; i < 10; i++) {
            taskArr[i].start();
        }

        //运行过程中如果线程的状态和初始状态不一样就将状态变化过程写入到文件中
        boolean finish = false;

        while (!finish) {
            for (int i = 0; i < 10; i++) {
                //让线程状态发生变化
                if (taskArr[i].getState() != threadStates[i]) {

                    //打印当前线程信息
                    printThreadMsg(printWriter, taskArr[i], threadStates[i]);

                    //将当前线程状态保存到线程状态数组中
                    threadStates[i] = taskArr[i].getState();
                }
            }
            finish = true;
            for (int i = 0; i < 10; i++) {
                finish = finish && (taskArr[i].getState() == Thread.State.TERMINATED);
            }
        }
    }

    //打印当前线程的信息
    private static void printThreadMsg(PrintWriter printWriter, Thread thread, Thread.State threadState) {
        printWriter.println("*********************************************************");
        printWriter.println("线程ID:" + thread.getId() + "线程名称:" + thread.getName());
        printWriter.println("线程优先级：" + thread.getPriority());
        printWriter.println("线程过去状态：" + threadState);
        printWriter.println("线程当前状态：" + thread.getState());
        printWriter.println("*********************************************************");
    }
}
