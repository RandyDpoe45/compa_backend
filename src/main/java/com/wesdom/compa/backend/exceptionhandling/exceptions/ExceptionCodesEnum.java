/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.compa.backend.exceptionhandling.exceptions;

import lombok.Getter;

/**
 *
 * @author randy
 */
@Getter
public enum ExceptionCodesEnum {
    
    UNEXPECTED_ERROR("001"),
    PRODUCT_EXISTING_REFERENCE("002"),
    CSV_LOAD_FAILURE("003"),
    UNPARSEABLE_JSON("004"),
    INVENTORY_WITH_RELATION("005"),
    EXISTING_SUPPLY("006"),
    EXISTING_INVENTORY("007"),
    EXISTING_PRODUCT("008"),
    EXISTING_WAREHOUSE("009"),
    WAREHOUSE_WITH_INVENTORIES("010"),
    EXISTING_ALLY("011"),
    ERROR_UPLOADING_FILES("012"),
    CORRUPTED_FILE("013"),
    USERNAME_IN_USE("014"),
    EMAIL_IN_USE("015"),
    ADDRESS_NAME_IN_USE("016"),
    BAD_CREDENTIALS("017"),
    NOT_ENOUGH_STOCK("018"),
    ORDER_WITHOUT_PRODUCTS("019");
    
    private String code;
    
    private ExceptionCodesEnum (String code){
        this.code = code;
    }
    
}
