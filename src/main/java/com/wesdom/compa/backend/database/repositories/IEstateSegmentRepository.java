package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.EstateSegmentType;
import com.wesdom.compa.backend.database.model.EstateSegment;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IEstateSegmentRepository {
    public EstateSegment get(Long id);
    public Page<EstateSegment> getAll(Map<String,String> params);
    public EstateSegment create(EstateSegment estateSegment);
    public EstateSegment update(Long estateSegmentId, EstateSegment estateSegment);
    public void delete(Long estateSegmentId);
    public List<EstateSegmentType> getEstateSegmentTypeList();
}
