package com.kyle.learn.aqx;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by kyle.xu on 2016/5/18.
 */
public class DataCheckV2 {

    public static Map<String,String> categoryMap = new HashMap<>();

    static {
        String line = "";
        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("F:\\data\\AQPlus3\\product.txt"), Charset.forName("UTF-8"))) {
            while ((line=bufferedReader.readLine())!=null){
                String[] split = line.split("\\^");

                categoryMap.put(split[20], split[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        List<AdsVo> adsVoList = new ArrayList<>();
        String line = "";
        String fileLoc = "F:\\data\\AQPlus3\\20160302.txt";
        System.out.println(LocalDateTime.now()+"开始生成list");
        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileLoc), Charset.forName("UTF-8"))) {
//        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileLoc), Charset.forName("GBK"))) {
            while ((line=bufferedReader.readLine())!=null){
                String[] split = line.split("\\^",-1);
                String category = categoryMap.get(split[5])==null?"NA":categoryMap.get(split[5]);
                Long duration = Long.valueOf(split[11]);
                Double cost = Double.valueOf(split[12]);
                /*平面
                Long duration = Long.valueOf(0);
                Long cost = Long.valueOf(split[11]);*/

                //互联网
                /*Long duration = Long.valueOf(0);
                Double cost = Double.valueOf(split[6]);*/

                AdsVo adsVo = new AdsVo(category,duration,cost);
                adsVoList.add(adsVo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(LocalDateTime.now()+"开始计算");
        //计算数据
        Map<String, Double> cost = adsVoList.stream().collect(
                Collectors.groupingBy(AdsVo::getCategory, Collectors.summingDouble(AdsVo::getCost)));

        Map<String, Long> duration = adsVoList.stream().collect(
                Collectors.groupingBy(AdsVo::getCategory, Collectors.summingLong(AdsVo::getDuration)));

        Map<String, Long> count = adsVoList.stream().collect(
                Collectors.groupingBy(AdsVo::getCategory,Collectors.summingLong(p->1)));


        for (Map.Entry<String,Long>entry:count.entrySet()){
            System.out.println(entry.getKey()+": 条数->"+entry.getValue()+",时长->"+duration.get(entry.getKey())+
                    ",花费->"+cost.get(entry.getKey()));
        }
        System.out.println(LocalDateTime.now()+"Game Over！");
    }



}
