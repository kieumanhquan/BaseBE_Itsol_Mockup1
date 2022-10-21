package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unit,Integer> {
    @Query("select d from Unit d where d not in ( select u.unit from User u where u.id = :cid)")
    List<Unit> findUnitNotJoinUser(@Param("cid") Long id);
}
