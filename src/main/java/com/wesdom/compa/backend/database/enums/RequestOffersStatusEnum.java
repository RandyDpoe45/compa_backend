package com.wesdom.compa.backend.database.enums;

import lombok.Getter;

@Getter
public enum RequestOffersStatusEnum {

    ACCEPTED("1"),
    VERIFYING("2"),
    DECLINED("3"),
    DELIVERED("4"),
    PAID("5");

    private String code;

    private RequestOffersStatusEnum(String code) {
        this.code = code;
    }

}
