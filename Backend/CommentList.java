package com.cuhksz.musicfour;

import java.util.ArrayList;
import java.util.List;



public class CommentList {
    private List<Comment> commentList = new ArrayList<>();

    public CommentList() {
        ConnectMySql dataBase = new ConnectMySql();
        this.commentList = dataBase.getCommentListFromMySql();
    }

    public void addComment(Comment comment){
        this.commentList.add(comment);
    }

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