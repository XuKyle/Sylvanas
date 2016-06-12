package com.kyle.learn.threads;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by kyle.xu on 2016/5/26.
 */
public class Main {
    public static void main(String[] args) {
        /*for (int i = 0; i <= 10; i++) {
            Calculator calculator = new Calculator(i);
            Thread thread = new Thread(calculator);
            thread.start();
        }*/

        Thread[] threads = new Thread[10];
        Thread.State[] status = new Thread.State[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Calculator(i));
            if (i % 2 == 0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread:" + i);
        }

        try (
                FileWriter file = new FileWriter("F:\\log.txt");
                PrintWriter printWriter = new PrintWriter(file);
        ) {
            for (int i = 0; i < 10; i++) {
                printWriter.println("Main : Status of Thread " + i + " : " + threads[i].getState());
                status[i] = threads[i].getState();
            }

            for (int i = 0; i < 10; i++) {
                threads[i].start();
            }

            boolean finish = false;
            while (!finish) {
                for (int i = 0; i < 10; i++) {
                    if (threads[i].getState() != status[i]) {
                        writeThreadInfo(printWriter, threads[i], status[i]);
                        status[i] = threads[i].getState();
                    }
                }
                finish = true;
                for (int i = 0; i < 10; i++) {
                    System.out.println("before:" + finish);
                    finish = finish && (threads[i].getState() == Thread.State.TERMINATED);
                    System.out.println("after:" + finish);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeThreadInfo(PrintWriter printWriter, Thread thread, Thread.State state) {
        printWriter.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
        printWriter.printf("Main : Priority: %d\n", thread.getPriority());
        printWriter.printf("Main : Old State: %s\n", state);
        printWriter.printf("Main : New State: %s\n", thread.getState());
        printWriter.printf("Main : ************************************\n");
    }
}
