package com.wesdom.compa.backend.database.enums;

import lombok.Getter;

@Getter
public enum RequestStatusEnum {

    SENT("SENT"),
    ACCEPTED("ACCEPTED"),
    IN_NEGOTIATION("IN_NEGOTIATION"),
    NEGOTIATED("NEGOTIATED"),
    NOTIFIED("NOTIFIED"),
    IN_PRODUCTION("IN_PRODUCTION"),
    DECLINED("DECLINED"),
    DELIVERED("DELIVERED"),
    PAID("PAID"),
    CLOSED("CLOSED");

    private String code;

    private RequestStatusEnum(String code) {
        this.code = code;
    }

}
