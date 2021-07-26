package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.model.MeasureUnit;
import com.wesdom.compa.backend.database.model.Product;
import com.wesdom.compa.backend.database.repositories.IMeasureUnitRepository;
import com.wesdom.compa.backend.database.repositories.IProductRepository;
import com.wesdom.compa.backend.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.compa.backend.exceptionhandling.exceptions.GeneralException;
import com.wesdom.compa.backend.service.interfaces.IMeasureUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasureUnitServiceImpl implements IMeasureUnitService {

    @Autowired
    private IMeasureUnitRepository measureUnitRepository;

    @Autowired
    private IProductRepository productRepository;

    @Override
    public void deleteMeasureUnit(Long id) {
        Product product = productRepository.findTop1ByMeasureUnitId(id);
        if(product != null){
            throw new GeneralException(
                    ExceptionCodesEnum.MEASURE_UNIT_ASSIGNED,
                    "No se puede eliminar esta unidad de medida, porque algunos productos ya la tienen asociada"
            );
        }
        measureUnitRepository.delete(id);
    }
}
