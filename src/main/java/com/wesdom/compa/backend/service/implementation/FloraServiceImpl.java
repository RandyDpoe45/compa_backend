package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.model.NearbyFlora;
import com.wesdom.compa.backend.database.repositories.IFloraRepository;
import com.wesdom.compa.backend.database.repositories.INearbyFloraRepository;
import com.wesdom.compa.backend.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.compa.backend.exceptionhandling.exceptions.GeneralException;
import com.wesdom.compa.backend.service.interfaces.IFloraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FloraServiceImpl implements IFloraService {

    @Autowired
    private INearbyFloraRepository nearbyFloraRepository;

    @Autowired
    private IFloraRepository floraRepository;

    @Override
    public void deleteFlora(Long id) {
        NearbyFlora nearbyFlora = nearbyFloraRepository.findTop1ByFloraId(id);
        if (nearbyFlora != null){
            throw new GeneralException(
                    ExceptionCodesEnum.FLORA_IN_USE,
                    "Esta flora no puede ser eliminada porque esta en uso."
            );
        }
        floraRepository.delete(id);
    }
}
