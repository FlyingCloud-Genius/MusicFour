package com.cuhksz.musicfour;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Download {
    private String ip = "120.78.82.130";
    private int port = 3306;
    private String dbName = "MusicFour";
    private String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName;
    private String user = "root";
    private String password = "123456";
    private String musicID;


    public void downloadMp3(String musicFakeID) {
        this.musicID = musicFakeID;
        downloadMusicThread.start();
        try {
            downloadMusicThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    final Thread downloadMusicThread = new Thread(new Runnable() {
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
                    File dir = new File("D:\\MusicFour\\");
                    Statement statement = conn.createStatement();
                    String findSql = "select MscName, MscFile from music where MscID ='"+musicID+"'";
                    ResultSet rs = statement.executeQuery(findSql);
                    while(rs.next()) {
                        Blob blob = rs.getBlob("MscFile");
                        String MscName = rs.getString("MscName");
                        InputStream in = blob.getBinaryStream();
                        File file = new File(dir, MscName);
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        FileOutputStream fout = null;
                        try {
                            fout = new FileOutputStream(file);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        int b = -1;
                        try {
                            while((b=in.read())!=-1){
                                fout.write(b);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Succuessful Download! " + MscName);
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

    public static void main(String[] args) {
        Download connector = new Download();
        String musicFakeID = "M36345";
        connector.downloadMp3(musicFakeID);
    }
}
