package com.itsol.recruit.service;

import com.itsol.recruit.entity.Transfer;

import java.util.List;

public interface TransferService {
    public List<Transfer> getAllTransfer();
    public Transfer createTranfer(Transfer transfer);
    public Transfer updateTransfer(Transfer transfer);
    public void deleteById(Integer id);
    public Transfer getById(Integer id);

}
