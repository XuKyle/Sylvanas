package com.kyle.learn.aqx;

/**
 * Created by kyle.xu on 2016/5/18.
 */
public class AdsVo {
    private String category;
    private Long duration;
    private Double cost;

    public AdsVo(String category, Long duration, Double cost) {
        this.category = category;
        this.duration = duration;
        this.cost = cost;
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
