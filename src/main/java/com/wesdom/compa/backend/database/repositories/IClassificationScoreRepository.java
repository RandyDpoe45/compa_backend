package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.ClassificationScore;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IClassificationScoreRepository {
    public ClassificationScore get(Long id);
    public Page<ClassificationScore> getAll(Map<String,String> params);
    public ClassificationScore getClassificationScoreBetweenRange(Float score);
    public ClassificationScore save(ClassificationScore classificationScore);
    public ClassificationScore update(Long classificationScoreId, ClassificationScore classificationScore);
    public void delete(Long classificationScoreId);
}
