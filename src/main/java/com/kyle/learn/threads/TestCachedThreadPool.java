package com.kyle.learn.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kyle.xu on 2016/5/30.
 */
public class TestCachedThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.submit(new TestRunnable());
            System.out.println("************* a" + i + " *************");
        }
        service.shutdown();
    }
}

class TestRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" 线程被调用了！");
    }
}
