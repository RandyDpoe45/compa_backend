package com.wesdom.compa.backend.database.enums;

public enum ManufacturerTypeEnum {
    BEEKEEPER("1"),AGRIGULTURAL("2");

    private String code;

    private ManufacturerTypeEnum(String code){
        this.code = code;
    }
}
