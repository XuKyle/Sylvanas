package com.kyle.learn.ftp;

import it.sauronsoftware.ftp4j.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by kyle on 2016/5/2.
 */
public class Ftp4jApiDemo {
    public static void main(String[] args) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("szftp.nielsenccdata.com",21);
            ftpClient.login("wujiang","wujiang");
//            下面是匿名登录
//            client.login("anonymous", "密码任意设置");
//            client.login("anonymous", "ftp4j");

            //强制退出
//            ftpClient.disconnect(true);
            //安全退出
//            ftpClient.disconnect(false);

            String currentDirectory = ftpClient.currentDirectory();
            System.out.println("currentDirectory = " + currentDirectory);

            ftpClient.changeDirectory("/atad/");
            FTPFile[] ftpFiles = ftpClient.list();
            for(FTPFile file:ftpFiles){
                System.out.println(file.getName());
            }

            ftpClient.download("A3文件测试.txt",new java.io.File("H:\\FTP\\A3文件测试.txt"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (FTPIllegalReplyException e) {
            e.printStackTrace();
        } catch (FTPException e) {
            e.printStackTrace();
        } catch (FTPDataTransferException e) {
            e.printStackTrace();
        } catch (FTPListParseException e) {
            e.printStackTrace();
        } catch (FTPAbortedException e) {
            e.printStackTrace();
        }


    }


}
