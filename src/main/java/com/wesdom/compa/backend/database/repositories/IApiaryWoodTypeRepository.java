package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.estatesegment.ApiaryWoodType;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IApiaryWoodTypeRepository {
    public ApiaryWoodType get(Long id);
    Page<ApiaryWoodType> getAll(Map<String, String> params);
    public ApiaryWoodType save(ApiaryWoodType apiaryWoodType);
    public ApiaryWoodType update(Long estateSegmentId, ApiaryWoodType apiaryWoodType);
    public void delete(Long estateSegmentId);
}
