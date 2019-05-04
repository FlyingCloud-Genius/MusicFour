package com.cuhksz.musicfour;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectMySql {
    private String ip = "120.78.82.130";
    private int port = 3306;
    private String dbName = "MusicFour";
    private String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName;
    private String user = "root";
    private String password = "123456";
    private String userID;
    private String musicSheetID;
    private String musicSheetName;
    private String musicSheetInfo;

    private String regID;
    private String regPassword;
    private String userName;
    private String userGender;
    private String userBirth;
    private List<Comment> commentList = new ArrayList<>();
    private List<String> musicSheetList = new ArrayList<>();
    private List<SpecialMusicList> wholeMusicSheetList = new ArrayList<>();
    private List<SpecialMusic> wholeMusicList = new ArrayList<>();

    /*store userID and userPassword in database*/
    private List<String> regInfoList = new ArrayList<>();
    private List<Album> albumInfoList = new ArrayList<>();

    public List<Album> getAlbumInfo(){
        albumThread.start();
        try {
            albumThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.albumInfoList;
    }

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

    public List<String> getMusicSheetInfo(String userID, String musicSheetID) {
        this.userID = userID;
        this.musicSheetID = musicSheetID;
        musicSheetThread.start();
        try {
            musicSheetThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.musicSheetList;
    }

    public void insertRegistry(String email, String password, String name, String gender, String birthday, String userID) {
        this.regID = email;
        this.regPassword = password;
        this.userName = name;
        this.userGender = gender;
        this.userBirth = birthday;
        this.userID = userID;
        insertRegistryThread.start();
        try {
            insertRegistryThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteMusicSheet(String userID, String musicSheetID) {
        this.userID = userID;
        this.musicSheetID = musicSheetID;
        deleteMusicSheetThread.start();
        try {
            deleteMusicSheetThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insertMusicSheet(String musicSheetID, String musicSheetName, String musicSheetInfo, String userID) {
        this.userID = userID;
        this.musicSheetID = musicSheetID;
        this.musicSheetName = musicSheetName;
        this.musicSheetInfo = musicSheetInfo;
        insertMusicSheetThread.start();
        try {
            insertMusicSheetThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<SpecialMusicList> getWholeMusicSheetList(String userID) {
        this.userID = userID;
        wholeMusicSheetListThread.start();
        try {
            wholeMusicSheetListThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.wholeMusicSheetList;
    }

    public List<SpecialMusic> getWholeMusicList() {
        wholeMusicListThread.start();
        try {
            wholeMusicListThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.wholeMusicList;
    }

    public ConnectMySql() {
    }


    final Thread insertRegistryThread = new Thread(new Runnable() {
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
                    String sql = "insert into registry values('" + regID + "', '" + regPassword + "')";
                    statement.execute(sql);
                    System.out.println("Insert reg!!!!!");

                    sql = "insert into users values('" + userID + "', '" + userName + "', '" + userBirth + "', '', '" + userGender + "', '" + regID + "')";
                    statement.execute(sql);
                    System.out.println("Insert user!!!!!!");
                    conn.close();
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    });


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


    /* This method is for getting album information */
    final Thread albumThread = new Thread(new Runnable() {
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
                    String sql = "select AID, AName, p.PID, p.PName, APublishDate, m.MsnID, m.MsnName, imageID " +
                            "FROM album a, publisher p, musician m " +
                            "WHERE a.MsnID = m.MsnID AND a.PID = p.PID ";
                    ResultSet rs = statement.executeQuery(sql);
                    String AID = null;
                    String AName = null;
                    String PID = null;
                    String PName = null;
                    String APublishDate = null;
                    String MsnID = null;
                    String MsnName = null;
//                    String imageID = R.drawable.music00001;
                    while(rs.next()) {
                        AID = rs.getString("AID");
                        AName = rs.getString("AName");
                        PID = rs.getString("PID");
                        PName = rs.getString("PName");
                        APublishDate = rs.getString("APublishDate");
                        MsnID = rs.getString("MsnID");
                        MsnName = rs.getString("MsnName");
//                        imageID = rs.getString("iamgeID");

                        albumInfoList.add(new Album(AID, AName, PID, PName, APublishDate, MsnID, MsnName));

                        System.out.println("database regInfo: " + AID + " " + AName + " " + PID + " " + PName + " " + APublishDate + " " + MsnID + " " + MsnName);
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
    final Thread musicSheetThread = new Thread(new Runnable() {
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
                    String sql = "select MSName, MSInfo " +
                            "FROM musicsheet " +
                            "WHERE UID="+userID+" and MSID="+musicSheetID;
                    ResultSet rs = statement.executeQuery(sql);
                    String musicSheetInfo = null;
                    String musicSheetName = null;
                    while (rs.next()) {
                        musicSheetName = rs.getString("MSName");
                        musicSheetInfo = rs.getString("MSInfo");
                        musicSheetList.add(musicSheetName);
                        musicSheetList.add(musicSheetInfo);
                        System.out.println(musicSheetName + " " + musicSheetInfo);
                    }
                    rs.close();
                    conn.close();
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    final Thread deleteMusicSheetThread = new Thread(new Runnable() {
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
                    String sql1 = "delete FROM MS_include WHERE MSID="+musicSheetID;
                    String sql2 = "delete " +
                            "FROM musicsheet " +
                            "WHERE UID="+userID+" and MSID="+musicSheetID;
                    statement.execute(sql1);
                    statement.execute(sql2);
                    System.out.println("Deleted!");
                    conn.close();
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    final Thread insertMusicSheetThread = new Thread(new Runnable() {
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
                    String sql = "insert into musicsheet values('"+musicSheetID+"', '"+musicSheetName+"', '"+musicSheetInfo+"', '"+userID+"')";
                    statement.execute(sql);
                    System.out.println("Insert!");
                    conn.close();
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    final Thread wholeMusicSheetListThread = new Thread(new Runnable() {
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
                    String sql = "select MSID, MSName from musicsheet;";
                    ResultSet rs = statement.executeQuery(sql);
                    String msid = null;
                    String musicSheetName = null;
                    while (rs.next()) {
                        msid = rs.getString("MSID");
                        musicSheetName = rs.getString("MSName");
                        wholeMusicSheetList.add(new SpecialMusicList(msid, musicSheetName));
                    }
                    sql = "select MSID, count(MscID) FROM MS_include GROUP BY MSID;";
                    rs = statement.executeQuery(sql);
                    int countNum = 0;
                    while (rs.next()) {
                        msid = rs.getString("MSID");
                        countNum = rs.getInt(2);
                        for(SpecialMusicList tmp:wholeMusicSheetList) {
                            if(tmp.getMusicSheetID().equals(msid)) {
                                tmp.setCountMusicNum(countNum);
                            }
                        }
                    }
                    sql = "select MSID, MscID from MS_include;";
                    rs = statement.executeQuery(sql);
                    String musicID = null;
                    while (rs.next()) {
                        msid = rs.getString("MSID");
                        musicID = rs.getString("MscID");
                        for(SpecialMusicList tmp:wholeMusicSheetList) {
                            if(tmp.getMusicSheetID().equals(msid)) {
                                tmp.addMusicInclude(musicID);
                            }
                        }
                    }

                    rs.close();
                    conn.close();
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    final Thread wholeMusicListThread = new Thread(new Runnable() {
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
                    String sql = "select MscID, MscName, MsnName from music, musician where music.MsnID = musician.MsnID;";
                    ResultSet rs = statement.executeQuery(sql);
                    String MscID = null;
                    String MscName = null;
                    String MsnName = null;
                    while(rs.next()) {
                        MscID = rs.getString("MscID");
                        MscName = rs.getString("MscName");
                        MsnName = rs.getString("MsnName");
                        wholeMusicList.add(new SpecialMusic(MscID, MscName, MsnName));
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