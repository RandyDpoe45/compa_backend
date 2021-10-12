package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.model.estatesegment.BeekeepingSegment;
import com.wesdom.compa.backend.database.repositories.IApiaryWoodTypeRepository;
import com.wesdom.compa.backend.database.repositories.IEstateSegmentRepository;
import com.wesdom.compa.backend.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.compa.backend.exceptionhandling.exceptions.GeneralException;
import com.wesdom.compa.backend.service.interfaces.IWoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class WoodTypeServiceImpl implements IWoodTypeService {

    @Autowired
    private IApiaryWoodTypeRepository apiaryWoodTypeRepository;

    @Autowired
    private IEstateSegmentRepository estateSegmentRepository;

    @Override
    public void deleteWoodType(Long id) {
        Page<BeekeepingSegment> beekeepingSegments = estateSegmentRepository.getByWoodTypeId(id,1l);
        if(!beekeepingSegments.isEmpty()){
            throw new GeneralException(
                    ExceptionCodesEnum.WOOD_TYPE_IN_USE,
                    "Este tipo de madera esta en uso."
            );
        }else{
            apiaryWoodTypeRepository.delete(id);
        }
    }
}
