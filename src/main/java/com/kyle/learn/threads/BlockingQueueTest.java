package com.kyle.learn.threads;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by kyle.xu on 2016/6/12.
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        final int FILE_QUEUE_SIZE = 10;
        final int SEARCH_THREAD = 10;
        BlockingQueue<File> queue = new ArrayBlockingQueue<File>(FILE_QUEUE_SIZE);
        String directory = "F:\\ftp\\radioads";
        String keyword = "20160202^";

        FileEnumerationTask fileEnumerationTask = new FileEnumerationTask(queue,new File(directory));
        new Thread(fileEnumerationTask).start();
        for (int i = 0; i < SEARCH_THREAD; i++) {
            new Thread(new SearchTask(queue,keyword)).start();
        }
    }
}

class FileEnumerationTask implements Runnable{

    public static File DUMMY = new File("");
    private BlockingQueue<File> queue;
    private File startingDirectory;

    public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory) {
        this.queue = queue;
        this.startingDirectory = startingDirectory;
    }

    @Override
    public void run() {
        try{
            enumerate(startingDirectory);
            queue.put(DUMMY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        for(File file:files){
            if(file.isDirectory()){
                enumerate(file);
            }else{
                queue.put(file);
            }
        }
    }
}

class SearchTask implements Runnable{
    private BlockingQueue<File> queue;
    private String keyword;

    public SearchTask(BlockingQueue<File> queue, String keyword) {
        this.queue = queue;
        this.keyword = keyword;
    }

    @Override
    public void run() {
        try{
            boolean done = false;
            while (!done){
                File file = queue.take();
                if(file == FileEnumerationTask.DUMMY){
                    queue.put(file);
                    done = true;
                }else {
                    search(file);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void search(File file) throws IOException{
        System.out.println("processing file --> "+file.getName());
        try (Scanner in = new Scanner(file)){
            int lineNumber = 0;
            while (in.hasNextLine()){
                lineNumber++;
                String line = in.nextLine();
                if(line.contains(keyword)){
                    System.out.printf("%s:%d;%s%n",file.getPath(),lineNumber,line);
                }
            }
        }
    }
}
