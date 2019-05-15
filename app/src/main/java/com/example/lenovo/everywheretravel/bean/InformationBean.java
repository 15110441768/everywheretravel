package com.example.lenovo.everywheretravel.bean;

public class InformationBean {
    String name;
    String time;
    String content;

    public InformationBean() {
    }

    public InformationBean(String name, String time, String content) {
        this.name = name;
        this.time = time;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "InformationBean{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
