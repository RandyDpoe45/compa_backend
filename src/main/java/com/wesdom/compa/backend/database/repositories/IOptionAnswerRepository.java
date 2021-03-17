package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.OptionAnswer;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IOptionAnswerRepository {
    public OptionAnswer get(Long id);
    public Page<OptionAnswer> getAll(Map<String,String> params);
    public OptionAnswer create(OptionAnswer activity);
    public OptionAnswer update(Long activityId, OptionAnswer activity);
    public void delete(Long activityId);
}
