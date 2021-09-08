package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.PackingOptionJpaRepository;
import com.wesdom.compa.backend.database.model.PackingOption;
import com.wesdom.compa.backend.database.repositories.IPackingOptionRepository;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PackingOptionRepositoryImpl implements IPackingOptionRepository {

    @Autowired
    private PackingOptionJpaRepository packingOptionJpaRepository;

    @Override
    public PackingOption get(Long id) {
        return packingOptionJpaRepository.getOne(id);
    }

    @Override
    public Page<PackingOption> getAll(Map<String, String> params) {
        IPredicateBuilder<PackingOption> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return packingOptionJpaRepository.findAll(
                predicate.createPredicate(params),
                paginaton.createPagination(params)
        );
    }

    @Override
    public PackingOption save(PackingOption packingOption) {
        return packingOptionJpaRepository.saveAndFlush(packingOption);
    }

    @Override
    public PackingOption update(Long packingOptionId, PackingOption packingOption) {
        PackingOption packingOption1 = packingOptionJpaRepository.getOne(packingOptionId);
        packingOption1.setDescription(packingOption.getDescription())
                .setProductType(packingOption.getProductType());
        return packingOptionJpaRepository.saveAndFlush(packingOption1);
    }

    @Override
    public void delete(Long packingOptionId) {
        packingOptionJpaRepository.deleteById(packingOptionId);
    }
}
