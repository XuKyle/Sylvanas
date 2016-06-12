package com.kyle.learn.threads;

/**
 * Created by kyle.xu on 2016/6/12.
 */
public class MuKeLesson1 {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(Thread.currentThread().getName());

        /*for (int i = 0; i < 3; i++) {
            new Thread(new TheThread(),"Thread["+i+"]").start();
        }*/
        try{
            Thread thread1 = new Thread(new TheThread(),"thread--1");
            thread1.start();
            thread1.sleep(2000);
            thread1.interrupt();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TheThread implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId()+":"+Thread.currentThread().getName()+":"+i);
        }
    }
}
