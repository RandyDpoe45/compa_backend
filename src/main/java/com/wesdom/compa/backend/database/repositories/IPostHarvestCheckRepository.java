package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.PostHarvestCheck;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IPostHarvestCheckRepository {
    public PostHarvestCheck get(Long id);
    public Page<PostHarvestCheck> getAll(Map<String,String> params);
    public PostHarvestCheck save(PostHarvestCheck postHarvestCheck);
    public PostHarvestCheck update(Long postHarvestCheckId, PostHarvestCheck postHarvestCheck);
    public void delete(Long postHarvestCheckId);
}
