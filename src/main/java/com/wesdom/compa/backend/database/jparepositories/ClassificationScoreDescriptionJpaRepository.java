package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.ClassificationScore;
import com.wesdom.compa.backend.database.model.ClassificationScoreDescription;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassificationScoreDescriptionJpaRepository
        extends JpaRepository<ClassificationScoreDescription, Long>,
        JpaSpecificationExecutor<ClassificationScoreDescription> {

    @Query("SELECT cs " +
            "FROM ClassificationScoreDescription cs " +
            "WHERE ?1 BETWEEN cs.classificationScore.lowerLimit AND cs.classificationScore.upperLimit " +
            "AND cs.entityName = ?2")
    List<ClassificationScoreDescription> findByScoreInRangeAndEntityName(Float score, String entityName, Pageable pageable);

    ClassificationScoreDescription findTop1ByClassificationScoreId(Long id);
}
