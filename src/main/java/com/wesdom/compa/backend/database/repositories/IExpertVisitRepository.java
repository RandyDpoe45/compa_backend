package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.ExpertVisit;
import com.wesdom.compa.backend.database.model.ExpertVisitType;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IExpertVisitRepository {
    public ExpertVisit get(Long id);
    Page<ExpertVisit> getAll(Map<String, String> params);
    public ExpertVisit save(ExpertVisit ExpertVisit);
    public ExpertVisit update(Long estateSegmentId, ExpertVisit ExpertVisit);
    public List<ExpertVisitType> getTypes();
    public void delete(Long estateSegmentId);
}
