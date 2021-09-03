package com.wesdom.compa.backend.service.interfaces;

import com.wesdom.compa.backend.database.model.users.Promoter;

public interface IPromoterService {
    Promoter createPromoter(Promoter promoter);
    Promoter updatePromoter(Long id, Promoter promoter);
}
