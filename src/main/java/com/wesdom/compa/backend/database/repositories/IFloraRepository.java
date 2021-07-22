package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.Flora;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IFloraRepository {
    public Flora get(Long id);
    Page<Flora> getAll(Map<String, String> params);
    public Flora create(Flora flora);
    public Flora update(Long floraId, Flora flora);
    public void delete(Long floraId);
}
