package com.cuhksz.musicfour;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLConnector {
    static Connection con;     //声明Connection对象
    static Statement sql;      //声明Statement对象
    static ResultSet res;      //声明ResultSet对象

    public Connection getConnection() {    //建立返回值为Connection的方法
        try {                             //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {                 //通过访问数据库的URL获取数据库连接对象
            con = DriverManager.getConnection("jdbc:mysql://120.78.82.130:3306/MusicFour", "root", "123456");
            System.out.println("数据库连接成功");
            System.out.print('\n');
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public ArrayList<String> getData(Connection con) {
        ArrayList<String> list = new ArrayList<>();
        try{
            sql = con.createStatement();     //实例化Statement对象
            res = sql.executeQuery("select * from users;");
            while(res.next()) {
                list.add(res.getString("UName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        MySQLConnector connector = new MySQLConnector();
        Connection conn = connector.getConnection();


        System.out.println(res);
    }
}
