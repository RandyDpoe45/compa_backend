package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.ExpertVisit;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IExpertVisitRepository {
    public ExpertVisit get(Long id);
    Page<ExpertVisit> getAll(Map<String, String> params);
    public ExpertVisit create(ExpertVisit ExpertVisit);
    public ExpertVisit update(Long estateSegmentId, ExpertVisit ExpertVisit);
    public void delete(Long estateSegmentId);
}
