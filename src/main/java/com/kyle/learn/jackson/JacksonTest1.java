package com.kyle.learn.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyle.learn.jackson.bean.Class;
import com.kyle.learn.jackson.bean.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kyle on 2016/4/26.
 */
public class JacksonTest1 {
    public static void main(String[] args) throws IOException {
        Student student = new Student(1,"kyle",new Date());
        ObjectMapper objectMapper = new ObjectMapper();

        // Convert object to JSON string
        String json = objectMapper.writeValueAsString(student);
        System.out.println("json = " + json);

        // Convert Json string to Object
        Student student1 = objectMapper.readValue(json,Student.class);
        System.out.println("student1 = " + student1);

        Student student2 = new Student(2,"mrh",new Date());
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(student);
        studentList.add(student2);

        Class clazz = new Class();
        clazz.setCname("三年二班");
        clazz.setMembers(studentList);

        String json2 = objectMapper.writeValueAsString(clazz);
        System.out.println("json2 = " + json2);

        Class clazz2 = objectMapper.readValue(json2,Class.class);
        System.out.println("clazz2 = " + clazz2);

    }


}
