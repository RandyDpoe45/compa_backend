package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.Manufacturer;
import com.wesdom.compa.backend.database.model.ManufacturerType;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IManufacturerRepository {
    public Manufacturer get(Long id);
    public Page<Manufacturer> getAll(Map<String,String> params);
    public Manufacturer create(Manufacturer manufacturer);
    public Manufacturer update(Long manufacturerId, Manufacturer manufacturer);
    public void delete(Long manufacturerId);
    public List<ManufacturerType> getManufacturerTypeList();
}
