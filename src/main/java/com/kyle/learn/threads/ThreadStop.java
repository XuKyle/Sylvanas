package com.kyle.learn.threads;

/**
 * Created by kyle.xu on 2016/6/12.
 */
public class ThreadStop {
    public static void main(String[] args) {
        try{
            Thread thread1 = new Thread(new myThread(),"thread[1]");
            thread1.start();
            Thread.sleep(2000);
            thread1.interrupt();
            System.out.println("lalala");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class myThread extends Thread{
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(this.isInterrupted()){
                break;
            }
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}
