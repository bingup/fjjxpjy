package com.fjjxpjy.pojo;

/**
 * @author fangjj
 * @date 2020/9/23
 * @description
 */
public class Dept {
    private Integer id;
    private String name;

    public Dept() {
    }

    public Dept(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
