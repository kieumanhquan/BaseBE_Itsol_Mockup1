package com.itsol.recruit.service;

import com.itsol.recruit.entity.Transfer;
import com.itsol.recruit.entity.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface TransferService {
    public List<Transfer> getAllTransfer();
    public Transfer createTranfer(Transfer transfer);
    public Transfer updateTransfer(Transfer transfer);
    public void deleteById(Integer id);
    public Transfer getById(Integer id);
    Page<Transfer> getTransFer(Pageable pageable);
    Page<Transfer> findTransfer(
            Pageable pageable,
            Unit unitDm,
            String name,
            String reason,
            Unit unitOld,
            Unit unitNew,
            Date succeeDay
    );

}
