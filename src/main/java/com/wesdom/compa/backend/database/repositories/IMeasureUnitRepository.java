package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.MeasureUnit;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IMeasureUnitRepository {
    public MeasureUnit get(Long id);
    public Page<MeasureUnit> getAll(Map<String,String> params);
    public MeasureUnit create(MeasureUnit measureUnit);
    public MeasureUnit update(Long manufacturerId, MeasureUnit measureUnit);
    public void delete(Long manufacturerId);
}
