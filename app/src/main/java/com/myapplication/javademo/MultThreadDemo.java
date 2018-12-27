package com.myapplication.javademo;

/**
 * Created by ${KZJ} on 2018/11/15.
 */
public class MultThreadDemo {
    public static void main(String[] args) {
        Task task = new Task();
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);
        Thread thread4 = new Thread(task);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        System.out.println("任务执行完成");
    }

}
