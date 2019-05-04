package com.cuhksz.musicfour;

import java.util.List;


public class Comment {
    private String commentName;
    private String comment;
    private List<Reply> replies;
    private String replyID;
    private String commentID;
    private String musicID;


    public Comment(String commentName, String comment, String commentID, String musicID, String replyID, List<Reply> replies){
        this.commentName = commentName;
        this.comment = comment;
        this.commentID = commentID;
        this.musicID = musicID;
        this.replyID = replyID;
        this.replies = replies;
    }

    public String getName() {
        return commentName;
    }

    public String getComment() {
        return comment;
    }

    public String getMusicID() {
        return musicID;
    }

    public String getReplyID() {
        return replyID;
    }

    public String getCommentID() {
        return commentID;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void addReply(String replyName, String reply){
        Reply newReply = new Reply(replyName,reply);
        this.getReplies().add(newReply);
    }
}