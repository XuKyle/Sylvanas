package com.kyle.learn.aqx;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by kyle.xu on 2016/6/12.
 */
public class DataSourceCheck {
    public static void main(String[] args) {
        String path = "F:\\ftp\\mobileplaylist\\20160515.txt";
        String line = "";
        int count = 0;
        boolean firstline = true;
        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)){
            while ((line=bufferedReader.readLine())!=null){
                    if(firstline){
                        count = StringUtils.countMatches(line,"^");
                        System.out.println("count = " + count);
                        firstline = false;
                    }
                if(count!=StringUtils.countMatches(line,"^")){
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
