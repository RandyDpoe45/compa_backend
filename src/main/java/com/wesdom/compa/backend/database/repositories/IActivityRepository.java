package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.activity.Activity;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IActivityRepository {
    public Activity get(Long id);
    public Page<Activity> getAll(Map<String,String> params);
    public Activity save(Activity activity);
    public Activity update(Long activityId, Activity activity);
    public void delete(Long activityId);
    public Activity findTop1ByProductionStageId(Long id);
}
