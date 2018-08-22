package com.agnt45.revaplacement.Classes;

public class Comment {


    public Comment(String commentMessage, String commentUsername, String commentUserpic, String commentUseruid) {
        this.commentMessage = commentMessage;
        this.commentUsername = commentUsername;
        this.commentUserpic = commentUserpic;
        this.commentUseruid = commentUseruid;
    }

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }

    public String getCommentUsername() {
        return commentUsername;
    }

    public void setCommentUsername(String commentUsername) {
        this.commentUsername = commentUsername;
    }

    public String getCommentUserpic() {
        return commentUserpic;
    }

    public void setCommentUserpic(String commentUserpic) {
        this.commentUserpic = commentUserpic;
    }

    public String getCommentUseruid() {
        return commentUseruid;
    }

    public void setCommentUseruid(String commentUseruid) {
        this.commentUseruid = commentUseruid;
    }

    String commentMessage;
    String commentUsername;
    String commentUserpic;
    String commentUseruid;


    public Comment() {
    }
}
