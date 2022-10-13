package com.itsol.recruit.service;

import com.itsol.recruit.entity.Unit;

import java.util.List;

public interface UnitService {
    public List<Unit> finAll();
    public Unit finById(Integer id);
    public Unit create(Unit unit);
    public Unit update(Unit unit);
}
