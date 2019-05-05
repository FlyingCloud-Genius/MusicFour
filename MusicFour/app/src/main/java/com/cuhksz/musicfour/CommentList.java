package com.cuhksz.musicfour;

import java.util.ArrayList;
import java.util.List;


public class CommentList {
    private List<Comment> commentList = new ArrayList<>();
    private List<Comment> tmpList = new ArrayList<>();
    private List<Comment> replyList = new ArrayList<>();

    public CommentList(){}

    public void buildCommentList() {
        commentList.clear();
        tmpList.clear();
        replyList.clear();
        ConnectMySql dataBase = new ConnectMySql();
        tmpList = dataBase.getCommentListFromMySql();
        for(Comment tmp:tmpList) {
            if(tmp.getReplyID() == null) {
                commentList.add(tmp);
            }
            else {
                replyList.add(tmp);
            }
        }
        for(Comment tmp:replyList) {
            for(Comment cmt:commentList) {
                if(tmp.getReplyID().equals(cmt.getCommentID())){
                    System.out.println(cmt.getCommentID());
                    cmt.addReply(tmp.getName(), tmp.getComment());
                }
            }
        }
    }

    public void addComment(Comment comment){
        this.commentList.add(comment);
    }

    public void clear(){this.commentList.clear();}

    public int size(){
        return this.commentList.size();
    }

    public Comment get(int i){
        return this.commentList.get(i);
    }

    public List<Comment> getCommentList(){
        return this.commentList;
    }
}