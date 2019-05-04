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

    public List<Comment> getCommentListFromMySql() {
        commentListThread.start();
        return this.commentList;
    }

    public ConnectMySql() {
    }

    final Thread commentListThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Connection conn = DriverManager.getConnection(url, user, password);
                    Statement statement = conn.createStatement();
                    String sql = "select UName, ComContent, ReplyTo " +
                            "FROM comments, users " +
                            "WHERE comments.UID = users.UID";
                    ResultSet rs = statement.executeQuery(sql);
                    String commentName=null;
                    String comment=null;
                    while(rs.next()) {
                        commentName = rs.getString("UName");
                        comment = rs.getString("ComContent");
                        commentList.add(new Comment(commentName, comment, null));
                    }
                    System.out.println(commentName + " " + comment);
                    rs.close();
                    conn.close();
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    });
}
