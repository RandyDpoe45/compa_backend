package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.PackingOption;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IPackingOptionRepository {
    public PackingOption get(Long id);
    public Page<PackingOption> getAll(Map<String,String> params);
    public PackingOption save(PackingOption packingOption);
    public PackingOption update(Long packingOptionId, PackingOption packingOption);
    public void delete(Long packingOptionId);
}
