package com.cuhksz.musicfour;

import java.util.List;

/**
 * Created by Mike on 2019/4/11.
 */

public class Comment {
    private String commentName;
    private String comment;
    private List<Reply> replies;


    public Comment(String commentName, String comment, List<Reply> replies){
        this.commentName = commentName;
        this.comment = comment;
        this.replies = replies;
    }

    public String getName() {
        return commentName;
    }

    public String getComment() {
        return comment;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void addReply(Comment targetComment,String replyName, String reply){
        Reply newReply = new Reply(replyName,reply);
        targetComment.getReplies().add(newReply);
    }
}