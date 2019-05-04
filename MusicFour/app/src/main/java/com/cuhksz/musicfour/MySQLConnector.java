package com.cuhksz.musicfour;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    public void insertBlob() {//插入Blob
        String localFIle = "D:\\Music\\911 - Alive.mp3";
        File dir = new File("D:\\MusicFour\\");
        File file = new File(dir, "copy.mp3");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
//            FileInputStream file = new FileInputStream(localFIle);
//            PreparedStatement ps = con.prepareStatement("insert into music values(?,?,?,?,?,?)");
//            ps.setString(1,"blob");
//            ps.setString(2,"Alive");
//            ps.setString(3, "adf");
//            ps.setString(4, "adf");
//            ps.setBinaryStream(5, file, file.available());
//            ps.setString(6,"1");
//            ps.executeUpdate();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select MscFile from music where MscID = 'blob'");
            while(rs.next()){
                Blob blob = rs.getBlob(1);
                InputStream in = blob.getBinaryStream();

                FileOutputStream fout = new FileOutputStream(file);
                int b = -1;
                while((b=in.read())!=-1){
                    fout.write(b);
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        MySQLConnector connector = new MySQLConnector();
        Connection conn = connector.getConnection();
        connector.insertBlob();

        System.out.println(res);
    }
}
