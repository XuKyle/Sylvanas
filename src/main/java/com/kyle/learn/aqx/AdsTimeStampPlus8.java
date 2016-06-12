package com.kyle.learn.aqx;

import org.apache.tools.ant.taskdefs.Local;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Created by kyle.xu on 2016/5/23.
 */
public class AdsTimeStampPlus8 {
    public static void main(String[] args) {

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        Instant instant = Instant.now();
        System.out.println("instant--->"+instant);

        //Current Date using LocalDate and LocalTime
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println("Current DateTime="+today);

        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(ZoneOffset.UTC.getId()));
        System.out.println("zonedDateTime--------"+zonedDateTime);




        System.out.println(ZoneId.systemDefault());

        System.out.println("----"+LocalDateTime.of(LocalDate.parse("20160601", DateTimeFormatter.BASIC_ISO_DATE),
                LocalTime.ofSecondOfDay(Long.parseLong("900"))));

        String string = LocalDateTime.of(LocalDate.parse("20160601", DateTimeFormatter.BASIC_ISO_DATE),
                LocalTime.ofSecondOfDay(Long.parseLong("900"))).toInstant(ZoneOffset.UTC).toString();

        String string1 = LocalDateTime.of(LocalDate.parse("20160601", DateTimeFormatter.BASIC_ISO_DATE),
                LocalTime.ofSecondOfDay(Long.parseLong("900"))).toInstant(ZoneOffset.of("+08:00")).toString();
        System.out.println("string = " + string+"----"+string1);
        System.out.println(Instant.parse("2016-05-23T06:42:09.104Z").toEpochMilli());
    }

    public static void process2(){
        String line = "";

        System.out.println(LocalDateTime.now());
        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("/data/test_data/kyle/tv_ads20150601.txt"));
            BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("/data/test_data/kyle/tv_ads20150601utc8.txt"))
        ) {
            while ((line=bufferedReader.readLine())!=null){
                String time = line.split("\\^")[0];

//                LocalDateTime.parse()

                line = line.replace(time,time+"+08:00");
                bufferedWriter.write(line+"\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void process1(){
        String line = "";

        System.out.println(LocalDateTime.now());
        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("/data/test_data/kyle/tv_ads20150601.txt"));
            BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("/data/test_data/kyle/tv_ads20150601utc8.txt"))
        ) {
            while ((line=bufferedReader.readLine())!=null){
                String time = line.split("\\^")[0];
                line = line.replace(time,time+"+08:00");
                bufferedWriter.write(line+"\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
