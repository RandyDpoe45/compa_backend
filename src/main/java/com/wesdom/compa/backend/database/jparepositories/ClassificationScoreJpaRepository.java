package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.ClassificationScore;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassificationScoreJpaRepository extends JpaRepository<ClassificationScore, Long>,
        JpaSpecificationExecutor<ClassificationScore> {

    @Query("SELECT cs FROM ClassificationScore cs WHERE ?1 BETWEEN cs.lowerLimit AND cs.upperLimit")
    List<ClassificationScore> findByScoreInRange(Float score, Pageable pageable);
}
