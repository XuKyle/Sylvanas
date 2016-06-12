package com.kyle.learn.aqx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * @Created by kyle.xu on 2016/5/24.
 * @Desc 本地数据生成hdfs存储的文件 --每个月存储过个文件
 */
public class AdsYearDistribute {

    public static void main(String[] args) {

        Path path = Paths.get("F:\\data\\AQPlusPool\\tvads");
        File[] files = path.toFile().listFiles();
        String line = "";

        for(File file:files){
            String month = file.getName().substring(0,6);
            Path destinationFile = Paths.get("F:\\data\\AQPlusPool\\tvadsmonth\\" + month + ".txt");
            if(!destinationFile.toFile().exists()){
                try {
                    destinationFile.toFile().createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try(BufferedReader bufferedReader = Files.newBufferedReader(file.toPath());
                BufferedWriter bufferedWriter = Files.newBufferedWriter(destinationFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND);) {
                while ((line=bufferedReader.readLine())!=null){
                    bufferedWriter.write(line+"\r\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }








}
