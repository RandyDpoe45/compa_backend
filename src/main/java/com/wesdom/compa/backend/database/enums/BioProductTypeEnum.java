package com.wesdom.compa.backend.database.enums;

import lombok.Getter;

@Getter
public enum BioProductTypeEnum {

    SYSTEM("SYSTEM"), ORIGINAL("ORGINAL");

    private String code;

    private BioProductTypeEnum(String code){
        this.code = code;
    }
}
