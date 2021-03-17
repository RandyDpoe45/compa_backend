package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.OptionAnswerJpaRepository;
import com.wesdom.compa.backend.database.model.OptionAnswer;
import com.wesdom.compa.backend.database.repositories.IOptionAnswerRepository;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OptionAnswerRepositoryImpl implements IOptionAnswerRepository {
    
    @Autowired
    private OptionAnswerJpaRepository optionAnswerJpaRepository;

    @Override
    public OptionAnswer get(Long id) {
        return optionAnswerJpaRepository.getOne(id);
    }

    @Override
    public Page<OptionAnswer> getAll(Map<String, String> params) {
        IPredicateBuilder<OptionAnswer> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return optionAnswerJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public OptionAnswer create(OptionAnswer optionAnswer) {
        return optionAnswerJpaRepository.save(optionAnswer);
    }

    @Override
    public OptionAnswer update(Long optionAnswerId, OptionAnswer optionAnswer) {
        OptionAnswer p = optionAnswerJpaRepository.getOne(optionAnswerId);
        p.setName(optionAnswer.getName());
        return  optionAnswerJpaRepository.save(p);
    }

    @Override
    public void delete(Long optionAnswerId) {
        optionAnswerJpaRepository.deleteById(optionAnswerId);
    }
}
