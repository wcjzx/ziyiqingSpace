package com.example.pojo.request;

public  class LikeUpdateRequest {
    private String aid;
    private int likeCount;

    // 省略getter和setter方法
    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
