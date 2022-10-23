package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Role;
import com.itsol.recruit.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UnitRepository extends JpaRepository<Unit,Integer> {
}
