package com.kyle.learn.threads;

import java.util.Date;

/**
 * Created by kyle.xu on 2016/6/3.
 */
public class Event{
    private Date date;
    private String event;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}