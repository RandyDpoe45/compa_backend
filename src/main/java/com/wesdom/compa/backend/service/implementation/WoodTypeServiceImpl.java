package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.repositories.IApiaryWoodTypeRepository;
import com.wesdom.compa.backend.service.interfaces.IWoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WoodTypeServiceImpl implements IWoodTypeService {

    @Autowired
    private IApiaryWoodTypeRepository apiaryWoodTypeRepository;

    @Override
    public void deleteWoodType(Long id) {

    }
}
