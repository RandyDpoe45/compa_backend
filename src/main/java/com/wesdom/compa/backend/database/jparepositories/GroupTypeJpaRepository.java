package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.GroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupTypeJpaRepository extends JpaRepository<GroupType,Long>, JpaSpecificationExecutor<GroupType> {
}
