package com.wesdom.compa.backend.database.enums;

public enum GroupTypeEnum {

    FAMILY("F","Familia");

    private String name;
    private String code;

    private GroupTypeEnum(String code,String name){
        this.code = code;
        this.name = name;
    }
}
