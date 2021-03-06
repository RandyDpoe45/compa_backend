package com.wesdom.compa.backend.database.enums;

import lombok.Getter;

@Getter
public enum UserTypeEnum {

    COMMERCIAL_PARTNER("COMMERCIAL_PARTNER"),MANUFACTURER("MANUFACTURER"), PROMOTER("PROMOTER");

    private String type;

    private UserTypeEnum(String type){
        this.type = type;
    }
}
