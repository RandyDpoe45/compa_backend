package com.wesdom.compa.backend.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.compa.backend.database.jparepositories.ProductJpaRepository;
import com.wesdom.compa.backend.database.model.Manufacturer;
import com.wesdom.compa.backend.database.model.Product;
import com.wesdom.compa.backend.database.model.ProductType;
import com.wesdom.compa.backend.database.repositories.IProductRepository;
import com.wesdom.compa.backend.dtos.GeneralResponse;
import com.wesdom.compa.backend.dtos.views.SystemViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/product")
public class ProductRestController {

    @Autowired
    private IProductRepository productRepository;

    @GetMapping
    @JsonView(SystemViews.ProductBasicView.class)
    public Page<Product> getAll(@RequestParam Map<String,String> params){
        return productRepository.getAll(params);
    }

    @GetMapping("/types")
    public List<ProductType> get(){
        return productRepository.getAllTypes();
    }

    @GetMapping("/{id}")
    @JsonView(SystemViews.ProductBasicView.class)
    public Product get(@PathVariable Long id){
        return productRepository.get(id);
    }

    @PostMapping
    @JsonView(SystemViews.ProductBasicView.class)
    public Product create(@RequestBody Product product){
        return productRepository.create(product);
    }

    @PutMapping(value = "/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @JsonView(SystemViews.ProductBasicView.class)
    public Product update(@PathVariable Long id, @RequestBody Product product) throws JsonProcessingException {
        return productRepository.update(id,product);
    }

    @DeleteMapping("/{id}")
    public GeneralResponse delete(@PathVariable Long id){
        productRepository.delete(id);
        return new GeneralResponse("Producto borrado con exito","000");
    }
}
