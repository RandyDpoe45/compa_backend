package com.wesdom.compa.backend.database.repositoriesimpl;

import com.wesdom.compa.backend.database.jparepositories.ManufacturerGroupJpaRepository;
import com.wesdom.compa.backend.database.jparepositories.GroupTypeJpaRepository;
import com.wesdom.compa.backend.database.model.ManufacturerGroup;
import com.wesdom.compa.backend.database.model.GroupType;
import com.wesdom.compa.backend.database.repositories.IGroupRepository;
import com.wesdom.compa.backend.service.implementation.PaginationBuilderImpl;
import com.wesdom.compa.backend.service.implementation.PredicateBuilderServiceImpl;
import com.wesdom.compa.backend.service.interfaces.IPaginationBuilder;
import com.wesdom.compa.backend.service.interfaces.IPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Service
public class GroupRepositoryImpl implements IGroupRepository {

    @Autowired
    private ManufacturerGroupJpaRepository groupJpaRepository;

    @Autowired
    private GroupTypeJpaRepository groupTypeJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public ManufacturerGroup get(Long id) {
        return groupJpaRepository.getOne(id);
    }

    @Override
    public Page<ManufacturerGroup> getAll(Map<String, String> params) {
        IPredicateBuilder<ManufacturerGroup> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginaton = new PaginationBuilderImpl();
        return groupJpaRepository.findAll(predicate.createPredicate(params),paginaton.createPagination(params));
    }

    @Override
    public ManufacturerGroup create(ManufacturerGroup group) {
        ManufacturerGroup g =  groupJpaRepository.saveAndFlush(group);
        em.refresh(g);
        return g;
    }

    @Override
    public ManufacturerGroup update(Long groupId, ManufacturerGroup group) {
        ManufacturerGroup g = groupJpaRepository.getOne(groupId);
        g.setGroupType(group.getGroupType()).setName(group.getName()).setManufacturerList(group.getManufacturerList());
        g = groupJpaRepository.saveAndFlush(g);
        em.refresh(g);
        return g;
    }

    @Override
    public void delete(Long groupId) {
        groupJpaRepository.deleteById(groupId);
    }

    @Override
    public List<GroupType> getGroupTypeList() {
        return groupTypeJpaRepository.findAll();
    }
}
