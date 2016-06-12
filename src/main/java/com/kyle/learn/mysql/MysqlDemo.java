package com.kyle.learn.mysql;

import javax.xml.transform.Result;
import java.sql.*;

/**
 * Created by kyle.xu on 2016/5/12.
 */
public class MysqlDemo {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        String sql;
        String url = "jdbc:mysql://192.168.253.186:3306/druid?"
                + "user=root&password=!QAZ2wsx#EDC&useUnicode=true&characterEncoding=UTF8";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动程序");
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            sql = "select *  from t1 where json_extract(jdoc,'$.key2') = 'value2'";

            ResultSet rs = stmt.executeQuery(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
            while (rs.next()){
                System.out.println(rs.getString(1));
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            conn.close();
        }


    }

}
