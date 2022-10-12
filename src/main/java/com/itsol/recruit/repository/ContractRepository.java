package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Contract;
import com.itsol.recruit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Integer> {
    @Query("SELECT c FROM Contract c where c.user = :cid")
    public List<Contract> findByUser(@Param("cid") User user);
}
