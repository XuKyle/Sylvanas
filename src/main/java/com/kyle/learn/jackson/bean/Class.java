package com.kyle.learn.jackson.bean;

import java.util.List;

/**
 * Created by kyle on 2016/4/26.
 */
public class Class {
    private String cname;
    private List<Student> members;

    public Class() {
    }

    public Class(String cname, List<Student> members) {
        this.cname = cname;
        this.members = members;
    }

    @Override
    public String toString() {
        return "Class{" +
                "cname='" + cname + '\'' +
                ", members=" + members +
                '}';
    }

    public List<Student> getMembers() {
        return members;
    }

    public void setMembers(List<Student> members) {
        this.members = members;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
