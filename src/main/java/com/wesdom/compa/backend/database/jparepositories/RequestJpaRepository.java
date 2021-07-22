package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestJpaRepository extends JpaRepository<Request,Long>, JpaSpecificationExecutor<Request> {

    Request findTop1ByProductId(Long id);
}
