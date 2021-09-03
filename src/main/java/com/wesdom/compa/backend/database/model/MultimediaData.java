/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author randy
 */

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class MultimediaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({SystemViews.GeneralView.class})
    private Long id;

    @JsonView({SystemViews.GeneralView.class})
    private String imgType;

    @JsonView({SystemViews.GeneralView.class})
    private String entityName;

    @JsonView({SystemViews.GeneralView.class})
    private Long entityId;

    @Column(length = 700)
    @JsonView({SystemViews.GeneralView.class})
    private String fileName;

    @Column(length = 255)
    @JsonView({SystemViews.GeneralView.class})
    private String tag;

    public MultimediaData(
            String imgType,
            String entityName,
            Long entityId,
            String fileName) {
        this.imgType = imgType;
        this.entityName = entityName;
        this.entityId = entityId;
        this.fileName = fileName;
    }
}
