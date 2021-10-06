package com.wesdom.compa.backend.database.enums;

import lombok.Getter;

@Getter
public enum RequestOffersStatusEnum {

    ACCEPTED("ACCEPTED"),
    VERIFYING("VERIFYING"),
    DECLINED("DECLINED"),
    DELIVERED("DELIVERED"),
    PAID("PAID");

    private String code;

    private RequestOffersStatusEnum(String code) {
        this.code = code;
    }

}
