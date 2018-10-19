package com.zl.weilu.recyclerview.bean;

/**
 * @Description:
 * @Author: weilu
 * @Time: 2018/10/18 0018 10:36.
 */
public class TestBean {
    
    private int id;
    private String name;

    public TestBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
