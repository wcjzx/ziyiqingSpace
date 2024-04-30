package com.example.pojo.request;

public  class CollectUpdateRequest {
    private String aid;
    private int collectCount;

    // 省略getter和setter方法
    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }
}
