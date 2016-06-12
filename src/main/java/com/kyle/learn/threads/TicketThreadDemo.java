package com.kyle.learn.threads;

/**
 * Created by kyle.xu on 2016/5/30.
 */
public class TicketThreadDemo {
    public static void main(String[] args) {
        MyTicketThread myTicketThread = new MyTicketThread();

        new Thread(myTicketThread,"1").start();
        new Thread(myTicketThread,"2").start();
        new Thread(myTicketThread,"3").start();
    }
}

class MyTicketThread implements Runnable{
    private int ticket = 500;
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(ticket>0){
                System.out.println(Thread.currentThread().getName()+" --> ticket = "+ ticket--);
            }
        }
    }
}
