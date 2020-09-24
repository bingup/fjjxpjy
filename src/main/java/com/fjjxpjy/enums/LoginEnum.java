package com.fjjxpjy.enums;

/**
 * @author fangjj
 * @date 2020/9/24
 * @description
 */
public enum LoginEnum {

    login_code("code");


    private String value;

    LoginEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }}
