package com.cuhksz.musicfour;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectMySql {
    private List<Comment> commentList = new ArrayList<>();
    private String ip = "120.78.82.130";
    private int port = 3306;
    private String dbName = "MusicFour";
    private String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName;
    private String user = "root";
    private String password = "123456";


    /*store userID and userPassword in database*/
    private List<String> regInfoList = new ArrayList<>();

    public List<String> getUserInfo(){
        registryThread.start();
        try {
            registryThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.regInfoList;
    }

    public List<Comment> getCommentListFromMySql() {
        commentListThread.start();
        try {
            commentListThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.commentList;
    }

    public ConnectMySql() {
    }

    final Thread commentListThread = new Thread(new Runnable() {
        @Override
        public void run() {
            synchronized (this) {
                while (!Thread.interrupted()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Connection conn = DriverManager.getConnection(url, user, password);
                        Statement statement = conn.createStatement();
                        String sql = "select comID, UName, ComContent, ReplyTo, MscID " +
                                "FROM comments, users " +
                                "WHERE comments.UID = users.UID";
                        ResultSet rs = statement.executeQuery(sql);
                        String commentName = null;
                        String comment = null;
                        String commentID = null;
                        String musicID = null;
                        String ReplyTo = null;
                        while (rs.next()) {
                            List<Reply> emptyReply = new ArrayList<>();
                            commentName = rs.getString("UName");
                            comment = rs.getString("ComContent");
                            commentID = rs.getString("comID");
                            musicID = rs.getString("MscID");
                            ReplyTo = rs.getString("ReplyTo");
                            commentList.add(new Comment(commentName, comment, commentID, musicID, ReplyTo, emptyReply));
                            System.out.println(commentName + " " + comment);
                        }
                        rs.close();
                        conn.close();
                        return;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    });


    /* This method is for getting registry information */
    final Thread registryThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Connection conn = DriverManager.getConnection(url, user, password);
                    Statement statement = conn.createStatement();
                    String sql = "select RID, RPassword " +
                            "FROM registry ";
                    ResultSet rs = statement.executeQuery(sql);
                    String RID=null;
                    String RPassword=null;
                    while(rs.next()) {
                        RID = rs.getString("RID");
                        RPassword = rs.getString("RPassword");
                        String Info = RID + ":" + RPassword;
                        System.out.println("database regInfo: " + RID + " " + RPassword);
                        System.out.println("Info: " + Info);
                        regInfoList.add(Info);
                    }
                    rs.close();
                    conn.close();
                    return;
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    });
}