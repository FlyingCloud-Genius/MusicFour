package com.cuhksz.musicfour;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 2019/4/12.
 */

public class CommentList {
    private List<Comment> commentList = new ArrayList<>();

    public CommentList(){
        Reply reply1 = new Reply("B君","+1，超喜欢这首歌");
        List<Reply> replies1 = new ArrayList<>();
        replies1.add(reply1);
        Comment comment1 = new Comment("A君","高中是班级合唱比赛的歌，特别喜欢",replies1);
        this.commentList.add(comment1);
        Comment comment2 = new Comment("C君","火影里面最喜欢的歌了",null);
        this.commentList.add(comment2);
        Comment comment3 = new Comment("D君","拔剑吧！！！！！！！",null);
        this.commentList.add(comment3);
        Comment comment4 = new Comment("E君","Aimer的声音好好听",null);
        this.commentList.add(comment4);
        Comment comment5 = new Comment("F君","3A组合无敌了",null);
        this.commentList.add(comment5);
        Reply reply2 = new Reply("G君","此生无悔入洪门");
        Reply reply3 = new Reply("H君","可惜再次被灭门");
        List<Reply> replies2 = new ArrayList<>();
        replies2.add(reply2);
        replies2.add(reply3);
        Comment comment6 = new Comment("I君","求带炎黄不要角",replies2);
        this.commentList.add(comment6);
        Comment comment7 = new Comment("J君","第一",null);
        this.commentList.add(comment7);
        Comment comment8 = new Comment("K君","哈哈哈哈哈哈哈",null);
        this.commentList.add(comment8);
        Comment comment9 = new Comment("L君","刘通好聪明",null);
        this.commentList.add(comment9);
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