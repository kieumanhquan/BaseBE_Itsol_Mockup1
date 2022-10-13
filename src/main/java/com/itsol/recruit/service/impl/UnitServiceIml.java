package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Unit;
import com.itsol.recruit.repository.UnitRepository;
import com.itsol.recruit.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceIml implements UnitService {
    @Autowired
    private UnitRepository unitRepo;
    @Override
    public List<Unit> finAll() {
        return unitRepo.findAll();
    }

    @Override
    public Unit finById(Integer id) {
        return unitRepo.findById(id).get();
    }

    @Override
    public Unit create(Unit unit) {
        return unitRepo.save(unit);
    }

    @Override
    public Unit update(Unit unit) {
        return unitRepo.save(unit);
    }
}
