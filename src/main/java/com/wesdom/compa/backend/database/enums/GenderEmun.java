package com.wesdom.compa.backend.database.enums;

public enum GenderEmun {
    MALE("M"),FEMALE("F");

    private String code;

    private GenderEmun(String code){
        this.code = code;
    }
}
