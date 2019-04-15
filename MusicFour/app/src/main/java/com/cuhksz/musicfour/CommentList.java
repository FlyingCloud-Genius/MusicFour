package com.cuhksz.musicfour;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 2019/4/12.
 */

public class CommentList {
    private List<Comment> commentList = new ArrayList<>();

    public CommentList(){
        Reply reply1 = new Reply("B��","+1����ϲ�����׸�");
        List<Reply> replies1 = new ArrayList<>();
        replies1.add(reply1);
        Comment comment1 = new Comment("A��","�����ǰ༶�ϳ������ĸ裬�ر�ϲ��",replies1);
        this.commentList.add(comment1);
        Comment comment2 = new Comment("C��","��Ӱ������ϲ���ĸ���",null);
        this.commentList.add(comment2);
        Comment comment3 = new Comment("D��","�ν��ɣ�������������",null);
        this.commentList.add(comment3);
        Comment comment4 = new Comment("E��","Aimer�������ú���",null);
        this.commentList.add(comment4);
        Comment comment5 = new Comment("F��","3A����޵���",null);
        this.commentList.add(comment5);
        Reply reply2 = new Reply("G��","�����޻������");
        Reply reply3 = new Reply("H��","��ϧ�ٴα�����");
        List<Reply> replies2 = new ArrayList<>();
        replies2.add(reply2);
        replies2.add(reply3);
        Comment comment6 = new Comment("I��","����׻Ʋ�Ҫ��",replies2);
        this.commentList.add(comment6);
        Comment comment7 = new Comment("J��","��һ",null);
        this.commentList.add(comment7);
        Comment comment8 = new Comment("K��","��������������",null);
        this.commentList.add(comment8);
        Comment comment9 = new Comment("L��","��ͨ�ô���",null);
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