package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.PostHarvestCheckJpaRepository;
import com.wesdom.compa.backend.database.model.PostHarvestCheck;
import com.wesdom.compa.backend.database.model.Product;
import com.wesdom.compa.backend.database.repositories.IPostHarvestCheckRepository;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PostHarvestCheckRepositoryImpl implements IPostHarvestCheckRepository {

    @Autowired
    private PostHarvestCheckJpaRepository postHarvestCheckJpaRepository;

    @Override
    public PostHarvestCheck get(Long id) {
        return postHarvestCheckJpaRepository.getOne(id);
    }

    @Override
    public Page<PostHarvestCheck> getAll(Map<String, String> params) {
        IPredicateBuilder<PostHarvestCheck> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return postHarvestCheckJpaRepository.findAll(
                predicate.createPredicate(params),
                paginaton.createPagination(params)
        );
    }

    @Override
    public PostHarvestCheck save(PostHarvestCheck postHarvestCheck) {
        return postHarvestCheckJpaRepository.saveAndFlush(postHarvestCheck);
    }

    @Override
    public PostHarvestCheck update(Long postHarvestCheckId, PostHarvestCheck postHarvestCheck) {
        PostHarvestCheck postHarvestCheck1 = postHarvestCheckJpaRepository.getOne(postHarvestCheckId);
        postHarvestCheck1.setDescription(postHarvestCheck.getDescription());
        return postHarvestCheckJpaRepository.saveAndFlush(postHarvestCheck1);
    }

    @Override
    public void delete(Long postHarvestCheckId) {
        postHarvestCheckJpaRepository.deleteById(postHarvestCheckId);
    }
}
