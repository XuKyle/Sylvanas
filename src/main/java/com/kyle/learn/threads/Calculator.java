package com.kyle.learn.threads;

/**
 * Created by kyle.xu on 2016/5/26.
 */
public class Calculator implements Runnable {
    private int number;

    public Calculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            System.out.printf("%s:%d*%d = %d\n",Thread.currentThread().getName(),number,i,i*number);
        }
    }
}
