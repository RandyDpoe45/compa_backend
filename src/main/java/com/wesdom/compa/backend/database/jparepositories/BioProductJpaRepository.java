package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.BioProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BioProductJpaRepository extends JpaRepository<BioProduct,Long> , JpaSpecificationExecutor<BioProduct> {
//    List<BioProduct> findAllById(List<Long> ids);
}
