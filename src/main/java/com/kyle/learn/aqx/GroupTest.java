package com.kyle.learn.aqx;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by kyle.xu on 2016/5/18.
 */
public class GroupTest {
    public static void main(String[] args) {
        List<AdsVo> adsVoList = new ArrayList<>();
        AdsVo adsVo = new AdsVo("1",2L,2D);
        AdsVo adsVo1 = new AdsVo("1",3L,2D);
        AdsVo adsVo2 = new AdsVo("2",4L,3D);
        AdsVo adsVo3 = new AdsVo("2",5L,3D);
        AdsVo adsVo4 = new AdsVo("3",6L,3D);
        adsVoList.add(adsVo);
        adsVoList.add(adsVo1);
        adsVoList.add(adsVo2);
        adsVoList.add(adsVo3);
        adsVoList.add(adsVo4);

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


    }
}
