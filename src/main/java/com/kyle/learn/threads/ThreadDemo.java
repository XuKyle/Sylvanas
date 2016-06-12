package com.kyle.learn.threads;

import java.util.concurrent.*;

/**
 * Created by kyle.xu on 2016/5/24.
 */
public class ThreadDemo {
    public static void main(String[] args) {

        /*Runnable task = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.println("foo:" + name);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("bar:" + name);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        task.run();

        Thread thread = new Thread(task);
        thread.start();

        System.out.println("Done!");*/

/*
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello:" + threadName);
            try {
                TimeUnit.MINUTES.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }*/

        Callable<Integer> task = ()->{
          try{
              TimeUnit.SECONDS.sleep(1);
              return 123;
          }catch (InterruptedException e){
              throw new IllegalStateException("task interrupted", e);
          }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(task);
        System.out.println("future down? "+future.isDone());

        Integer result = null;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("future done? " + future.isDone());
        System.out.print("result: " + result);



    }


}
