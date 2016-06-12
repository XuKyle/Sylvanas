package com.kyle.learn.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.util.JSON;
import org.apache.commons.lang3.*;
import org.bson.*;
import org.bson.types.ObjectId;
import org.codehaus.groovy.util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static java.util.Arrays.asList;

/**
 * Created by kyle.xu on 2016/5/31.
 */
public class App {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("192.168.253.186",27000);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("aqplus");

//        MongoCollection<BasicDBObject> media = mongoDatabase.getCollection("product_category1", BasicDBObject.class);
    /*    MongoCollection<Document> media = mongoDatabase.getCollection("custom_report_template_group");
        MongoCursor<Document> iterator = media.find().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().get("_id").getClass());
        }*/

                /*Insert a Document*/
        MongoCollection<Document> kyle = mongoDatabase.getCollection("kyle");
        Document document = new Document("name","MongoDb").append("type","database")
                .append("count",1).append("info",new Document("x",203).append("y",102));
        kyle.insertOne(document);
        System.out.println(document.get("_id"));
        System.out.println(document.get("_id").toString());
        System.out.println(document.get("_id").getClass());

        Document first = kyle.find(Filters.eq("_id", document.get("_id"))).first();
        System.out.println("first = " + first);
        Document second = kyle.find(Filters.eq("_id", document.get("_id").toString())).first();
        System.out.println("second = " + second);

        System.out.println(new ObjectId().toString());


        /*MongoCollection<BasicDBObject> media = mongoDatabase.getCollection("media", BasicDBObject.class);
        DistinctIterable<String> print_category_id = media.distinct("print_category_id", String.class);
        for(String s :print_category_id){
            System.out.println("----"+s);
        }*/



//        customer_group,customer

      /*  String line = "";
        MongoCollection<BasicDBObject> collection = mongoDatabase.getCollection("customer", BasicDBObject.class);

        collection.drop();

        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("F:\\data\\AQPlusHdfs\\result2.txt"))){
            while ((line=bufferedReader.readLine())!=null){
                *//*if(StringUtils.countMatches(line,"[")!=StringUtils.countMatches(line,"]")){
                    System.out.println(line);
                }*//*

                System.out.println(line);
//                DBObject dbObject = (DBObject) JSON.parse(line.substring(0,line.length()-1));
                DBObject dbObject = (DBObject) JSON.parse(line);
                collection.insertOne((BasicDBObject) dbObject);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(line);
        }*/

        /*String json = "{\"name\":\"kyle\", \"age\":30,\"info\":{\"lalala\":\"haha\",\"ooo\":\"hehe\"}}";
        DBObject dbObject = (DBObject) JSON.parse(json);

        MongoCollection<BasicDBObject> collection = mongoDatabase.getCollection("kyle", BasicDBObject.class);
        collection.insertOne((BasicDBObject) dbObject);

        BasicDBObject first6 = collection.find().first();
        System.out.println("first6 = " + first6.get("name"));*/

      /*
        添加数组
        MongoCollection<Document> collection = mongoDatabase.getCollection("kyle_test_aderviser");
        Document document = new Document();
        document.append("_id","1");
        document.append("cn","啦啦啦");
        document.append("us","lalala");
        List<String> category1 = new ArrayList<String>(){{add("1");add("2");}};
        document.append("category1",category1);

        List<String> category2 = new ArrayList<String>(){{add("11");add("21");}};
        document.append("category2",category2);

        collection.insertOne(document);*/

        /*查询数据*/
        /*MongoCollection<Document> advertiser = mongoDatabase.getCollection("advertiser");

        MongoCursor<Document> category1_id = advertiser.find(Filters.in("category1_id", new ArrayList<String>(){{add("21");add("1");}}))
                .projection(Projections.include("_id","eng_name","chi_name"))
                .iterator();
        while (category1_id.hasNext()){
            System.out.println(category1_id.next().toJson());
        }*/

        /*测试效率Instant start = Instant.now();
        MongoCollection<Document> adVersion = mongoDatabase.getCollection("ad_version");
        Document chi_name = adVersion.find(Filters.eq("chi_name", "产品(05G")).first();

        FindIterable<Document> category1_id = adVersion.find(Filters.eq("category1_id", "3"));
        for (Document cur : category1_id) {
            System.out.println(cur.toJson());
        }

        System.out.println(chi_name.toJson());
        Instant stop = Instant.now();
        System.out.println(Duration.between(start,stop).toMillis());*/

//        报表模板添加
      /*  List<Document> documents = new ArrayList<>();
        MongoCollection<Document> system_report_template = mongoDatabase.getCollection("system_report_template");
        system_report_template.drop();

        documents.add(new Document("eng_name","").append("chi_name","常规报表").append("eng_alias","").append("chi_alias","常").append("_id","1").append("image","").append("color",""));
        documents.add(new Document("eng_name","").append("chi_name","副本报表").append("eng_alias","").append("chi_alias","副").append("_id","2").append("image","").append("color",""));
        documents.add(new Document("eng_name","").append("chi_name","新业务报表").append("eng_alias","").append("chi_alias","新").append("_id","3").append("image","").append("color",""));
        documents.add(new Document("eng_name","").append("chi_name","例外报表").append("eng_alias","").append("chi_alias","例").append("_id","4").append("image","").append("color",""));
        documents.add(new Document("eng_name","").append("chi_name","新业务例外报表").append("eng_alias","").append("chi_alias","新例").append("_id","5").append("image","").append("color",""));
        documents.add(new Document("eng_name","").append("chi_name","新增-保留-流失报表").append("eng_alias","").append("chi_alias","新保").append("_id","6").append("image","").append("color",""));
        documents.add(new Document("eng_name","").append("chi_name","广告流水报表").append("eng_alias","").append("chi_alias","详").append("_id","7").append("image","").append("color",""));

        system_report_template.insertMany(documents);*/

        /*Get a Collection*/
//        MongoCollection<Document> kyle = mongoDatabase.getCollection("kyle");

        /*Insert a Document*/
//        Document document = new Document("name","MongoDb").append("type","database").append("_id","001")
//                .append("count",1).append("info",new Document("x",203).append("y",102));

//        kyle.insertOne(document);

        /*Add Multiple Documents*/
/*        List<Document> documents = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            documents.add(new Document("i",i));
        }

        kyle.insertMany(documents);*/
/*

        */
/*Count Documents in A Collection*//*

        System.out.println("kyle.count() : "+kyle.count());

        */
/*Find the First Document in a Collection*//*

        Document myDoc = kyle.find().first();
        System.out.println("myDoc = " + myDoc.toJson());

        */
/*Find All Documents in a Collection*//*

        MongoCollection<Document> province = mongoDatabase.getCollection("province");
        MongoCursor<Document> cursor = province.find().iterator();
        try{
            while (cursor.hasNext()){
                System.out.println(cursor.next().toJson());
            }
        }finally {
            cursor.close();
        }

        */
/*Get A Single Document with a Query Filter*//*

        Document first = kyle.find(Filters.eq("i", 88)).first();
        System.out.println("first = " + first);
        System.out.println("first.toJson = " + first.toJson());

        */
/*Get a Set of Documents with a Query*//*

        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(Document document) {
                System.out.println(document.toJson());
            }
        };

        kyle.find(Filters.gt("i",90)).forEach(printBlock);
        System.out.println("----------and------------");
        kyle.find(Filters.and(Filters.gt("i",66),Filters.lt("i",70))).forEach(printBlock);

        */
/*Sorting documents*//*

        System.out.println("----------sort------------");
        Document first1 = kyle.find(Filters.exists("i")).sort(Sorts.descending("i")).first();
        System.out.println("first1 = " + first1.toJson());

        */
/*Projecting fields*//*

        Document first2 = kyle.find().projection(Projections.excludeId()).first();
        Document first3 = kyle.find().projection(Projections.exclude("type")).first();
        Document first4 = kyle.find().projection(Projections.exclude("_id")).first();
        System.out.println("first2.toJson() = " + first2.toJson());
        System.out.println("first3.toJson() = " + first3.toJson());
        System.out.println("first4.toJson() = " + first4.toJson());

        */
/*Aggregations*//*

        System.out.println("-----Aggregations-------");
        kyle.aggregate(asList(Aggregates.match(Filters.gt("i",0)),
                Aggregates.project(Document.parse("{ITimes10: {$multiply: ['$i', 10]}}"))))
                .forEach(printBlock);

        Document first5 = kyle.aggregate(Collections.singletonList(
                Aggregates.group(null, Accumulators.sum("total", "$i")))).first();

        System.out.println("first5.toJson() = " + first5.toJson());

        */
/*Updating documents*//*

        UpdateResult updateResult = kyle.updateOne(Filters.eq("name", "MongoDb"), Updates.set("name", "kyle"));
        System.out.println(updateResult.getModifiedCount());
        System.out.println(updateResult);

        */
/**//*

        BsonDocument bsonDocument = new BsonDocument().append("a", new BsonString("MongoDb"))
                .append("b", new BsonArray(Arrays.asList(new BsonInt32(2), new BsonInt32(24))));

//        kyle.insertOne(bsonDocument);

        // convert JSON to DBObject directly
        String json = "{\"name\":\"kyle\", \"age\":30,\"info\":{\"lalala\":\"haha\",\"ooo\":\"hehe\"}}";
        DBObject dbObject = (DBObject) JSON.parse(json);

        // Pass BasicDBObject.class as the second argument
        MongoCollection<BasicDBObject> collection = mongoDatabase.getCollection("kyle", BasicDBObject.class);
        collection.insertOne((BasicDBObject) dbObject);

        BasicDBObject first6 = collection.find().first();
        System.out.println("first6 = " + first6);

        MongoCursor<BasicDBObject> cursor1 = collection.find().iterator();
        while (cursor1.hasNext()){
            System.out.println(cursor1.next().toJson());
        }

        System.out.println("处理地市问题-----------------");
        Map documentMap = new HashMap<>();
        documentMap.put("_id","0535");
        documentMap.put("us","shandong");
        documentMap.put("cn","山东");
        List<Map> region = new ArrayList<>();
        Map regionMap = new HashMap<>();
        regionMap.put("_id","0531");
        regionMap.put("us","jn");
        regionMap.put("cn","济南");
        region.add(regionMap);
        Map regionMap1 = new HashMap<>();
        regionMap1.put("_id","0532");
        regionMap1.put("us","qd");
        regionMap1.put("cn","青岛");
        region.add(regionMap1);
        documentMap.put("region",region);
        collection.insertOne(new BasicDBObject(documentMap));

        BasicDBObject first7 = collection.find(Filters.eq("_id", "0535")).first();
        System.out.println("first7.toJson() = " + first7.toJson());
*/


    }


}
