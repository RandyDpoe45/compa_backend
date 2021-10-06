package com.wesdom.compa.backend.database.enums;

import lombok.Getter;

@Getter
public enum RequestStatusEnum {

    SENT("SENT"),
    ACCEPTED("ACCEPTED"),
    NEGOTIATED("NEGOTIATED"),
    NOTIFIED("NOTIFIED"),
    IN_PRODUCTION("IN_PRODUCTION"),
    DECLINED("DECLINED"),
    DELIVERED("DELIVERED"),
    PAID("PAID");

    private String code;

    private RequestStatusEnum(String code) {
        this.code = code;
    }

}
