package com.kyle.learn.threads;

/**
 * Created by kyle.xu on 2016/6/12.
 */
public class SharedVariableDemo {
    public static void main(String[] args) throws InterruptedException {
        TheThread theThread1=new TheThread();
        TheThread theThread2=new TheThread();
        theThread1.start();
        theThread2.start();
    }
    static int  i = 0;
    public static class TheThread extends Thread{
        public void run(){
            for(;i<100;){
                i++;
                System.out.println(Thread.currentThread().getId()+":"+i);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
