package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.compa.backend.database.model.Department;
import com.wesdom.compa.backend.database.model.ManufacturerGroup;
import com.wesdom.compa.backend.database.model.Municipality;
import com.wesdom.compa.backend.database.repositories.ILocationRepository;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/location")
public class LocationRestController {

    @Autowired
    private ILocationRepository locationRepository;

    @JsonView(SystemViews.LocationBasicView.class)
    @GetMapping("/municipality")
    public Page<Municipality> getAllMunicipalities(@RequestParam Map<String,String> allParams){
        return locationRepository.getAllMunicipalities(allParams);
    }

    @JsonView(SystemViews.LocationBasicView.class)
    @GetMapping("/department")
    public Page<Department> getAllDepartments(@RequestParam Map<String,String> allParams){
        return locationRepository.getAllDepartments(allParams);
    }
}
