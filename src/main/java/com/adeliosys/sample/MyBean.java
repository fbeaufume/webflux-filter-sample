package com.adeliosys.sample;

/**
 * Simple POJO with 2 attributes.
 */
public class MyBean {

    private String name;

    private int size;

    public MyBean() {
    }

    public MyBean(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
