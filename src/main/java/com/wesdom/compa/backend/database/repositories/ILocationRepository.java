package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.Department;
import com.wesdom.compa.backend.database.model.Municipality;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ILocationRepository {

    Page<Department> getAllDepartments(Map<String,String> params);
    Page<Municipality> getAllMunicipalities(Map<String,String> params);
}
