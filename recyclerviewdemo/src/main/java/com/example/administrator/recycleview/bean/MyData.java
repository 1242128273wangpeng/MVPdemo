package com.example.administrator.recycleview.bean;

/**
 * Created by Administrator on 2016/4/5.
 */
public class MyData {
    private String name;
    private String desc;
    private String icon;

    public MyData() {
    }

    public MyData(String desc, String icon, String name) {
        this.desc = desc;
        this.icon = icon;
        this.name = name;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyData{" +
                "desc='" + desc + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
