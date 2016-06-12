package com.kyle.learn.druid;

import java.util.List;

/**
 * Created by kyle.xu on 2016/5/11.
 */
public class QueryTimeseries {
    private String queryType = "timeseries";
    private String dataSource;
    private String descending;
    private List<String> intervals;
    private String granularity;
    private String filter;

    private List<Aggregation> aggregations;
    private List<Object> postAggregations;
    private String context;

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getDescending() {
        return descending;
    }

    public void setDescending(String descending) {
        this.descending = descending;
    }

    public List<String> getIntervals() {
        return intervals;
    }

    public void setIntervals(List<String> intervals) {
        this.intervals = intervals;
    }

    public String getGranularity() {
        return granularity;
    }

    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public List<Aggregation> getAggregations() {
        return aggregations;
    }

    public void setAggregations(List<Aggregation> aggregations) {
        this.aggregations = aggregations;
    }

    public List<Object> getPostAggregations() {
        return postAggregations;
    }

    public void setPostAggregations(List<Object> postAggregations) {
        this.postAggregations = postAggregations;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
