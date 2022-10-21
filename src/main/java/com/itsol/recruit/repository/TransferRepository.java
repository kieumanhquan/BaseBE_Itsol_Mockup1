package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Transfer;
import com.itsol.recruit.entity.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.Date;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {
    @Query("select t from TRANSFERS t")
    Page<Transfer> findTransfer(Pageable pageable);

    @Query("select t from TRANSFERS  t where ((t.unitOld = :unitDm or t.unitNew = :unitDm) or :unitDm is null) " +
            "and ((lower(t.transferName) like '%' || lower(:name) || '%' or :name is null) " +
            "and (lower(t.reasonTransfer) like '%' || lower(:reason) || '%' or :reason is null)" +
            "and (t.unitOld = :unitOld or :unitOld is null)" +
            "and (t.unitNew = :unitNew or :unitNew is null)" +
            "and  (t.transferDate = :successDay or :successDay is null))")
    Page<Transfer> searchTransfer(Pageable pageable,
                                  @Param("unitDm") Unit unitDm,
                                  @Param("name") String transferName,
                                  @Param("reason") String transferReason,
                                  @Param("unitOld") Unit unitOld,
                                  @Param("unitNew") Unit unitNew,
                                  @Param("successDay")Date successDay);

}
