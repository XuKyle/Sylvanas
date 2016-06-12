package com.kyle.learn.threads;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;

/**
 * Created by kyle.xu on 2016/6/3.
 */
public class CleanerTask extends Thread{

    public CleanerTask(Deque<Event> deque) {
        this.deque = deque;
    }

    private Deque<Event> deque;

    public Deque<Event> getDeque() {
        return deque;
    }

    public void setDeque(Deque<Event> deque) {
        this.deque = deque;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true){
            Date date = new Date();
            clean(date);
        }
    }

    private void clean(Date date) {
        long difference;
        boolean delete;
        if(deque.size()==0){
            return;
        }
        delete = false;
        do{
            Event event = deque.getLast();
            difference = date.getTime()- event.getDate().getTime();
//            System.out.println("difference:"+difference);
            if(difference>10000){
                System.out.printf("Cleaner:%s\n",event.getEvent());
                deque.removeLast();
                delete=true;
            }
        }while (difference>10000);

        if(delete){
            System.out.printf("Cleaner:Size of the deque:%d\n",deque.size());
        }


    }


    public static void main(String[] args) {
        Deque<Event> deque = new ArrayDeque<>();
        WriterTask writerTask = new WriterTask(deque);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(writerTask);
            thread.start();
        }

        CleanerTask cleanerTask = new CleanerTask(deque);
        cleanerTask.start();

    }
}
