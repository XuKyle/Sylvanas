package com.kyle.learn.druid;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by kyle.xu on 2016/5/30.
 */
public class JodaTimeDemo {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE");
        System.out.println(dateTimeFormatter.format(dateTime));

        System.out.println(DateTimeFormatter.ofPattern("w").format(dateTime));
        System.out.println(DateTimeFormatter.ofPattern("ee").format(dateTime));
        System.out.println(DateTimeFormatter.ofPattern("E").format(dateTime));
        System.out.println(DateTimeFormatter.ofPattern("Y").format(dateTime));
        System.out.println(DateTimeFormatter.ofPattern("D").format(dateTime));
        System.out.println(DateTimeFormatter.ofPattern("M").format(dateTime));
        System.out.println(DateTimeFormatter.ofPattern("d").format(dateTime));
        System.out.println(DateTimeFormatter.ofPattern("a").format(dateTime));
        System.out.println(DateTimeFormatter.ofPattern("K").format(dateTime));
        System.out.println(DateTimeFormatter.ofPattern("h").format(dateTime));
        System.out.println("HH"+DateTimeFormatter.ofPattern("HH").format(dateTime));
        System.out.println(DateTimeFormatter.ofPattern("k").format(dateTime));
        System.out.println(DateTimeFormatter.ofPattern("m").format(dateTime));
        System.out.println(DateTimeFormatter.ofPattern("s").format(dateTime));
        System.out.println(DateTimeFormatter.ofPattern("S").format(dateTime));
        System.out.println(dateTime);
//        System.out.println(DateTimeFormatter.ofPattern("z").format(dateTime));
//        System.out.println(DateTimeFormatter.ofPattern("Z").format(dateTime));


    }
}
