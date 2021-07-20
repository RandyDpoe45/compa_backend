package com.wesdom.compa.backend.database.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Advertising {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    //solo un producto??
    @ManyToOne(targetEntity = Association.class, fetch = FetchType.LAZY)
    private Association association;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToMany(targetEntity = ManufacturerGroup.class, fetch = FetchType.LAZY)
    private List<ManufacturerGroup> manufacturerGroupList;


}
