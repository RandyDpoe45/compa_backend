/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.MultimediaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author randy
 */
@Repository
public interface MultimediaDataJpaRepository extends JpaRepository<MultimediaData,Long>, JpaSpecificationExecutor<MultimediaData> {

    @Query("SELECT md FROM MultimediaData md WHERE md.entityId IN ?1 AND md.entityName = ?2 AND md.imgType = ?3")
    List<MultimediaData> findAllByIdAndEntityTypeAndImgType(List<Long> ids, String entityType, String imgType);
}
