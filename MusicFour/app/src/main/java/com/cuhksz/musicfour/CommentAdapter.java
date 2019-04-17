package com.cuhksz.musicfour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

/**
 * Created by Mike on 2019/4/11.
 */

public class CommentAdapter extends BaseExpandableListAdapter {
    private CommentList commentList;
    private Context context;

    public CommentAdapter(Context context, CommentList commentList){
        this.context = context;
        this.commentList = commentList;
    }

    @Override
    public int getGroupCount() {
        return commentList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        if (commentList.get(i).getReplies() == null || commentList.get(i).getReplies().size() == 0){
            return 0;
        }else {
            return commentList.get(i).getReplies().size();
        }
    }

    @Override
    public Object getGroup(int i) {
        return commentList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return commentList.get(i).getReplies().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return getCombinedChildId(i,i1);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        final GroupHolder groupHolder;
        int replyNum;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.comment_item, viewGroup, false);
            groupHolder = new GroupHolder(view);
            view.setTag(groupHolder);
        }else{
            groupHolder = (GroupHolder) view.getTag();
        }

        groupHolder.commenter.setText(commentList.get(i).getName());
        groupHolder.comment.setText(commentList.get(i).getComment());

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final ChildHolder childHolder;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.comment_reply_item, viewGroup, false);
            childHolder = new ChildHolder(view);
            view.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) view.getTag();
        }

        childHolder.replier.setText(commentList.get(i).getReplies().get(i1).getName());
        childHolder.reply.setText(commentList.get(i).getReplies().get(i1).getReply());

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    private class GroupHolder{
        private TextView commenter;
        private TextView comment;
        private TextView replies;
        public GroupHolder(View view){
            commenter = (TextView) view.findViewById(R.id.commenter);
            comment = (TextView) view.findViewById(R.id.comment);
            replies = (TextView) view.findViewById(R.id.reply);
        }
    }

    private class ChildHolder{
        private TextView replier;
        private TextView reply;
        public ChildHolder(View view){
            replier = (TextView) view.findViewById(R.id.replier);
            reply = (TextView) view.findViewById(R.id.reply);
        }
    }

}