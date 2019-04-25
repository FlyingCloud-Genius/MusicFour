package com.cuhksz.musicfour;

public class User {
    private String userName;
    private String userID;
    private String password;
    private String userInstruction;
    private boolean userGender;
    private boolean isVIP;
    private String VIPType;//normal, red, black
    private String durationLeft;

    //create an empty user with name(ID), random ID, no password, no instruction no gender,
    public User() {


    }

    public User(String userName, String userID, String password,
                String userInstruction, boolean userGender, boolean isVIP,
                String VIPType, String durationLeft) {
        this.userName = userName;
        this.userID = userID;
        this.password = password;
        this.userInstruction = userInstruction;
        this.userGender = userGender;
        this.isVIP = isVIP;
        this.VIPType = VIPType;
        this.durationLeft = durationLeft;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getUserInstruction() {
        return userInstruction;
    }

    public boolean isUserGender() {
        return userGender;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public String getVIPType() {
        return VIPType;
    }

    public String getDurationLeft() {
        return durationLeft;
    }
}
