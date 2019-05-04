package com.example.lenovo.everywheretravel.ui.details.bean;

public class DetailsBean {
    String time;
    String content;
    int type;

    public DetailsBean() {
    }

    public DetailsBean(String time, String content, int type) {
        this.time = time;
        this.content = content;
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DetailsBean{" +
                "time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                '}';
    }
}
