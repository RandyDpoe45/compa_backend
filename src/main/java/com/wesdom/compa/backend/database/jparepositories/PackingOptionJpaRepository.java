package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.PackingOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PackingOptionJpaRepository extends JpaRepository<PackingOption, Long>, JpaSpecificationExecutor<PackingOption> {
}
