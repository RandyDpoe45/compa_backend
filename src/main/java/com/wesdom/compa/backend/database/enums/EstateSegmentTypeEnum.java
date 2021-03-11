package com.wesdom.compa.backend.database.enums;

public enum EstateSegmentTypeEnum {
    BEEKEEPING("1","Apicultor"), AGRIGULTURAL("2","Agricola");

    private String code;
    private String name;

    private EstateSegmentTypeEnum(String code, String name){
        this.code = code;
        this.name = name;
    }
}
