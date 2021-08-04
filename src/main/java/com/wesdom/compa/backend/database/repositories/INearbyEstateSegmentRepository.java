package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.NearbyEstateSegment;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface INearbyEstateSegmentRepository {
    public NearbyEstateSegment get(Long id);
    Page<NearbyEstateSegment> getAll(Map<String, String> params);
    public NearbyEstateSegment save(NearbyEstateSegment nearbyEstateSegment);
    public NearbyEstateSegment update(Long estateSegmentId, NearbyEstateSegment nearbyEstateSegment);
    public void delete(Long estateSegmentId);
}
