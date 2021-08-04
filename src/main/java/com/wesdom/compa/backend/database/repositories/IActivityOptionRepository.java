package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.ActivityOption;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IActivityOptionRepository {
    public ActivityOption get(Long id);
    List<ActivityOption> findById(List<Long> ids);
    public Page<ActivityOption> getAll(Map<String,String> params);
    public ActivityOption save(ActivityOption activity);
    public ActivityOption update(Long activityId, ActivityOption activity);
    public void delete(Long activityId);
}
