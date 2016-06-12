package com.kyle.learn.threads;

import java.io.File;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by kyle.xu on 2016/5/30.
 */
public class FileSearch implements Runnable {
    private String initPath;
    private String fileName;

    public FileSearch(String initPath, String fileName) {
        this.initPath = initPath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        File file = new File(initPath);
        if (file.isDirectory()) {
            try {
                directoryProcess(file);
            } catch (InterruptedIOException e) {
                System.out.printf("%s: The search has been interrupted", Thread.currentThread().getName());
            }
        }
    }

    private void directoryProcess(File file) throws InterruptedIOException {
        File[] files = file.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    directoryProcess(files[i]);
                } else {
                    fileProcess(files[i]);
                }
            }
        }
        if (Thread.interrupted()) {
            throw new InterruptedIOException();
        }
    }

    private void fileProcess(File file) throws InterruptedIOException {
//        System.out.printf("processing --> %s \n",file.getName());
        if (file.getName().contains(fileName)) {
            System.out.printf("%s : %s \r\n", Thread.currentThread().getName(), file.getAbsolutePath());
        }
        if (Thread.interrupted()) {
            throw new InterruptedIOException();
        }
    }

    public static void main(String[] args) {
        FileSearch fileSearch = new FileSearch("F:\\tv", "太空");
        Thread thread = new Thread(fileSearch);
        thread.start();


        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();


    }
}
