package com.agnt45.revaplacement.Classes;

import android.support.v4.util.TimeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class PlacementEvent {
    private String Desc,picUrl;
    private Date timeofPost;
    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }







    public Date getTimeofPost() {
        return timeofPost;
    }

    public void setTimeofPost(Date timeofPost) {
        this.timeofPost = timeofPost;
    }

    public PlacementEvent(String desc, String picUrl, Date timeofPost) {
        Desc = desc;
        this.picUrl = picUrl;
        this.timeofPost = timeofPost;
    }




    public PlacementEvent(){
        //Empty Constructor
    }


}
