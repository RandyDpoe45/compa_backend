package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.users.Association;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IAssociationRepository {
    public Association get(Long id);
    public Page<Association> getAll(Map<String,String> params);
    public Association save(Association association);
    public Association update(Long associationId, Association association);
    public void delete(Long associationId);
}
