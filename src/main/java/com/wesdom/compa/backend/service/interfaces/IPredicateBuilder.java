/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.compa.backend.service.interfaces;

import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/**
 *
 * @author randy
 */
public interface IPredicateBuilder<T> {
    
    Specification<T> createPredicate(Map<String,String> params);
}
