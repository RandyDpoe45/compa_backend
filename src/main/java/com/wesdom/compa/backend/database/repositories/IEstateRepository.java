package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.estatesegment.Estate;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IEstateRepository {
    public Estate get(Long id);
    public Page<Estate> getAll(Map<String,String> params);
    public Estate save(Estate estate);
    public Estate update(Long estateId, Estate estate);
    public void delete(Long estateId);
}
