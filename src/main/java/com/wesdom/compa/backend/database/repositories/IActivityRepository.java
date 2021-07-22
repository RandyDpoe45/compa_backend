package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.Activity;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IActivityRepository {
    public Activity get(Long id);
    public Page<Activity> getAll(Map<String,String> params);
    public Activity create(Activity activity);
    public Activity update(Long activityId, Activity activity);
    public void delete(Long activityId);
    public Activity findTop1ByProductionStageId(Long id);
}
