package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.ClassificationScoreDescription;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IClassificationScoreDescriptionRepository {
    public ClassificationScoreDescription get(Long id);
    public Page<ClassificationScoreDescription> getAll(Map<String,String> params);
    public ClassificationScoreDescription getClassificationScoreBetweenRangeAndEntityName(Float score, String entityName);
    public ClassificationScoreDescription save(ClassificationScoreDescription classificationScore);
    public ClassificationScoreDescription update(Long classificationScoreId, ClassificationScoreDescription classificationScore);
    public ClassificationScoreDescription findTop1ByClassificationScoreId(Long id);
    public void delete(Long classificationScoreId);
}
