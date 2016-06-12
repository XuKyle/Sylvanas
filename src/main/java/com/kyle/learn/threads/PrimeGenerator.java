package com.kyle.learn.threads;

/**
 * Created by kyle.xu on 2016/5/30.
 */
public class PrimeGenerator extends Thread{
    @Override
    public void run() {
        long number = 1L;
        while (true){
            if(isPrime(number)){
                System.out.printf("Number %d is Prime\r\n",number);
            }
            if(isInterrupted()){
                System.out.printf("The Prime Generator has been Interruputed!\r\n");
                return;
            }
            number++;
        }
    }

    private boolean isPrime(long number) {
        if(number<=2){
            return true;
        }
        for (long i = 2; i < number; i++) {
            if(number%i==0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Thread thread = new PrimeGenerator();
        thread.start();
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
