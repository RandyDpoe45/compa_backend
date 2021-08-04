package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.Advertising;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IAdvertisingRepository {
    public Advertising get(Long id);
    public Page<Advertising> getAll(Map<String,String> params);
    public Advertising save(Advertising advertising);
    public Advertising update(Long advertisingId, Advertising advertising);
    public void delete(Long advertisingId);
}
