package com.kyle.learn.ftp;

import sun.net.TelnetInputStream;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;

/**
 * Created by kyle on 2016/4/30.
 */
public class JavaFtp {

    private String localFileName;
    private String remoteFileName;
    private FtpClient ftpClient;

    public void connectServer(String ip, int port, String user, String password, String path) {
        try {
            ftpClient = FtpClient.create();
            ftpClient.connect(new InetSocketAddress(ip,port));

//            ftpClient.login(user,null,password);
            ftpClient.login(user,password.toCharArray());

            // 设置成2进制传输
            ftpClient.setBinaryType();
            System.out.println("welComeMsg:"+ftpClient.getWelcomeMsg());
            System.out.println("workding dir:"+ftpClient.getWorkingDirectory());

            if (path.length() != 0) {
                // 把远程系统上的目录切换到参数path所指定的目录
                ftpClient.changeDirectory(path);
            }

        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void closeConnect() {
        try {
            ftpClient.close();
            System.out.println("disconnect success");
        } catch (IOException ex) {
            System.out.println("not disconnect");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void download(String remoteFile, String localFile) throws FtpProtocolException {
        InputStream is = null;
        FileOutputStream os = null;
        try {

            is = ftpClient.getFileStream(remoteFile);
            File file_in = new File(localFile);
            os = new FileOutputStream(file_in);

            byte[] bytes = new byte[1024];
            int c;
            while ((c = is.read(bytes)) != -1) {
                os.write(bytes, 0, c);
            }
            System.out.println("download success");
        } catch (IOException ex) {
            System.out.println("not download");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (os != null) {
                        os.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String agrs[]) {

        String filepath[] = {"/atad"};
        String localfilepath[] = {"H:\\FTP\\A3文件测试.txt"};
        JavaFtp ftp = new JavaFtp();
        /*
         * 使用默认的端口号、用户名、密码以及根目录连接FTP服务器
         */
        ftp.connectServer("szftp.nielsenccdata.com", 21, "wujiang", "wujiang", "/atad");



        // 下载
        for (int i = 0; i < filepath.length; i++) {
            try {
                ftp.download(filepath[i], localfilepath[i]);
            } catch (FtpProtocolException e) {
                e.printStackTrace();
            }
        }
        /*String localfile = "E:\\contact.txt";
        String remotefile = "/temp/records.txt";
        // 上传
        try {
            ftp.upload(localfile, remotefile);
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        }*/
        ftp.closeConnect();
    }

}
