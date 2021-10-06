package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.ExpertAlert;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IExpertAlertRepository {
    public ExpertAlert get(Long id);
    Page<ExpertAlert> getAll(Map<String, String> params);
    public ExpertAlert save(ExpertAlert ExpertAlert);
    public ExpertAlert update(Long estateSegmentId, ExpertAlert ExpertAlert);
    public void delete(Long estateSegmentId);
}
