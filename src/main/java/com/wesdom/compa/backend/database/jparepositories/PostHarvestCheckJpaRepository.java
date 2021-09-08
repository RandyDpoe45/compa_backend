package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.PostHarvestCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PostHarvestCheckJpaRepository extends JpaRepository<PostHarvestCheck, Long>, JpaSpecificationExecutor<PostHarvestCheck> {
}
