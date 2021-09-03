package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.users.Promoter;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IPromoterRepository {
    public Promoter get(Long id);
    public Page<Promoter> getAll(Map<String,String> params);
    public Promoter save(Promoter promoter);
    public Promoter update(Long promoterId, Promoter promoter);
    public void delete(Long promoterId);
}
