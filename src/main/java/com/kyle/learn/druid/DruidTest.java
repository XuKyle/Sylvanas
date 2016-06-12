package com.kyle.learn.druid;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyle.xu on 2016/5/11.
 */
public class DruidTest {

    public static void main(String[] args) {
        String druidUrl = "http://test06.datagroup.ccdata.com:8082/druid/v2/?pretty";
        QueryTimeseries queryTimeseries = new QueryTimeseries();
        queryTimeseries.setDataSource("tv_ads");
        queryTimeseries.setIntervals(new ArrayList<String>(){
            {add("2015-01-01T00:00:00.000+00:00/2016-01-01T00:00:00.000+00:00");}
        });

        queryTimeseries.setGranularity("all");
        queryTimeseries.setDescending("true");

        List<Aggregation> aggregations = new ArrayList<>();
        aggregations.add(new Aggregation("longSum","cost","cost"));
        aggregations.add(new Aggregation("longSum","duration","duration"));
        aggregations.add(new Aggregation("count","rows","rows"));
        queryTimeseries.setAggregations(aggregations);

        String toJSon = JSonUtils.toJSon(queryTimeseries);
        System.out.println("toJSon = " + toJSon);

        Instant start = Instant.now();
        System.out.println("开始查询时间："+ start);
        String post = HttpUtil.sendPost(druidUrl, toJSon);
        System.out.println("post = " + post);

        List readValue = JSonUtils.readValue(post, List.class);
        for (int i = 0; i < readValue.size(); i++) {
            System.out.println(readValue.get(i));
        }
//        System.out.println("readValue = " + readValue);


        Instant stop  = Instant.now();
        System.out.println("结束查询时间："+ stop);
        System.out.println("用时："+ Duration.between(start,stop).toMillis());

    }
}
