package com.example.androidhw;
import com.google.gson.annotations.SerializedName;

public class Video {
    @SerializedName("_id")
    public String _id;
    @SerializedName("feedurl")
    public String feedurl;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("description")
    public String description;
    @SerializedName("likecount")
    public Long likecount;
    @SerializedName("avatar")
    public String avatar;


}
