package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.ManufacturerGroup;
import com.wesdom.compa.backend.database.model.GroupType;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IGroupRepository {
    public ManufacturerGroup get(Long id);
    public Page<ManufacturerGroup> getAll(Map<String,String> params);
    public ManufacturerGroup save(ManufacturerGroup group);
    public ManufacturerGroup update(Long groupId, ManufacturerGroup group);
    public void delete(Long groupId);
    public List<GroupType> getGroupTypeList();
}
