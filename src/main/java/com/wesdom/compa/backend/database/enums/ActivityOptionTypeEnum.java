package com.wesdom.compa.backend.database.enums;

public enum ActivityOptionTypeEnum {

    OPEN("OPEN"),CLOSED("CLOSED");

    private String name;

    private ActivityOptionTypeEnum(String name){
        this.name = name;
    }
}
