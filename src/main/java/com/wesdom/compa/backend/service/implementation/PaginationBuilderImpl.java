/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.enums.PaginationParamsEnum;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.Map;

/**
 *
 * @author randy
 */
public class PaginationBuilderImpl implements IPaginationBuilder {

    @Override
    public Pageable createPagination(Map<String, String> params) {
        String pageSize = params.get(PaginationParamsEnum.PAGE_SIZE.getTag());
        String pageNumber = params.get(PaginationParamsEnum.PAGE_NUMBER.getTag());
        if(pageNumber != null && pageSize != null){
            if(Integer.parseInt(pageSize) < 1){
                return Pageable.unpaged();
            }
            String sortDir = params.get(PaginationParamsEnum.SORT_DIRECTION.getTag());
            String sortProp = params.get(PaginationParamsEnum.SORT_PROPERTY.getTag());
            if(sortProp != null){
                if(sortDir != null){
                    Direction direction =  sortDir.equals(PaginationParamsEnum.SORT_DIRECTION.getDefaultValue()) ?
                            Direction.DESC : Direction.ASC;
                    return PageRequest.of(
                            Integer.parseInt(pageNumber),
                            Integer.parseInt(pageSize),
                            Sort.by(direction, sortProp.split(","))
                    );
                }
                return PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize), Sort.by(sortProp.split(",")));
            }
            return PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
        }
        return Pageable.unpaged();
    }
    
}
