package com.cuhksz.musicfour;

/**
 * Created by Mike on 2019/4/12.
 */

public class Reply{
    private String replyName;
    private String reply;

    public Reply(String replyName, String reply){
        this.replyName = replyName;
        this.reply = reply;
    }

    public String getName(){
        return replyName;
    }

    public String getReply(){
        return reply;
    }

}