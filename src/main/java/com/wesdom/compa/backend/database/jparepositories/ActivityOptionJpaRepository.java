package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.activity.ActivityOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityOptionJpaRepository extends JpaRepository<ActivityOption,Long>, JpaSpecificationExecutor<ActivityOption> {
}
