package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.estatesegment.NearbyFlora;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface INearbyFloraRepository {
    public NearbyFlora get(Long id);
    Page<NearbyFlora> getAll(Map<String, String> params);
    public NearbyFlora save(NearbyFlora nearbyFlora);
    public NearbyFlora update(Long estateSegmentId, NearbyFlora nearbyFlora);
    public void delete(Long estateSegmentId);
    public NearbyFlora findTop1ByFloraId(Long id);
}
