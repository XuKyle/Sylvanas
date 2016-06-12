package com.kyle.learn.druid;

/**
 * Created by kyle.xu on 2016/5/11.
 */
public class Aggregation {
    private String type;
    private String name;
    private String fieldName;

    public Aggregation(String type, String name, String fieldName) {
        this.type = type;
        this.name = name;
        this.fieldName = fieldName;
    }

    public Aggregation(String type, String name) {

        this.type = type;
        this.name = name;
    }

    public Aggregation() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
