package com.kyle.learn.threads;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by kyle.xu on 2016/6/3.
 */
public class DataSourcesLoader implements Runnable {

    @Override
    public void run() {
        System.out.printf("Beginning data sources loading:%s\n",new Date());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("DataSources loading has finished %s\n",new Date());
    }

    public static void main(String[] args) {
        DataSourcesLoader dataSourcesLoader = new DataSourcesLoader();
        Thread thread1 = new Thread(dataSourcesLoader,"DataSourceThread");

        NetWorkConnectionsLoader netWorkConnectionsLoader = new NetWorkConnectionsLoader();
        Thread thread2 = new Thread(netWorkConnectionsLoader,"NetWorkConnect");

        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main:Configuration has been loaded:%s\n",new Date());

    }
}

class NetWorkConnectionsLoader implements Runnable{

    @Override
    public void run() {
        System.out.printf("Beginning NetWork loading:%s\n",new Date());
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("NetWork loading has finished %s\n",new Date());
    }
}
