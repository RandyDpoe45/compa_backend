/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.compa.backend.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 *
 * @author randy
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class GeneralResponse {
    
    private String response;
    private String errorCode;

    public GeneralResponse(String response, String code) {
        this.response = response;
        this.errorCode = code;
    }
    
    
}
