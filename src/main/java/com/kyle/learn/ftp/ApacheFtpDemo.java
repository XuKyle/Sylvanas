package com.kyle.learn.ftp;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.net.URLEncoder;

/**
 * Created by kyle on 2016/5/2.
 */
public class ApacheFtpDemo {
    public static void main(String[] args) {

        FTPClient ftpClient = new FTPClient();
        ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        try {
            // 1. 连接FTP服务器
            ftpClient.connect("szftp.nielsenccdata.com",21);
            // 2. 设置编码
            // 下面三行代码必须要，而且不能改变编码格式，否则不能正确下载中文文件
            ftpClient.setControlEncoding("UTF-8");
            FTPClientConfig ftpClientConfig = new FTPClientConfig(FTPClientConfig.SYST_NT);
            ftpClientConfig.setServerLanguageCode("zh");

            // 3. 登录ftp
            ftpClient.login("wujiang", "wujiang");

            // 看返回的值是不是230，如果是，表示登陆成功
            int replyCode = ftpClient.getReplyCode();
            System.out.println("replyCode = " + replyCode);

            FTPFile[] ftpFiles = ftpClient.listFiles();
            for(FTPFile ftpFile:ftpFiles){
                System.out.println(ftpFile.getName());
            }

/*
            boolean changeWorkingDirectory = ftpClient.changeWorkingDirectory("/atad/");
            System.out.println("changeWorkingDirectory = " + changeWorkingDirectory);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for(FTPFile ftpFile:ftpFiles){
                // 这个就就是弹出下载对话框的关键代码
//                response.setHeader("Content-disposition", "attachment;localAdr=" + URLEncoder.encode("H:\\FTP\\FTP文件.txt", "UTF-8"));
                // 将文件保存到输出流outputStream中
                File f=new File("H:\\FTP\\FTP文件.txt");
                OutputStream os=new FileOutputStream(f);
                ftpClient.retrieveFile(ftpFile.getName(),os);
                os.flush();
                os.close();
            }*/




        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
