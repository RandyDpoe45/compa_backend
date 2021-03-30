package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.Flora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FloraJpaRepository extends JpaRepository<Flora,Long>, JpaSpecificationExecutor<Flora> {
}
