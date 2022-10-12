package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Transfer;
import com.itsol.recruit.repository.TransferRepository;
import com.itsol.recruit.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferServiceIml implements TransferService {
    @Autowired
    private TransferRepository transferRepo;
    @Override
    public List<Transfer> getAllTransfer() {
        return transferRepo.findAll();
    }

    @Override
    public Transfer createTranfer(Transfer transfer) {
        return transferRepo.save(transfer);
    }

    @Override
    public Transfer updateTransfer(Transfer transfer) {
        return transferRepo.save(transfer);
    }

    @Override
    public void deleteById(Integer id) {
        transferRepo.deleteById(id);
    }

    @Override
    public Transfer getById(Integer id) {
        return transferRepo.getById(id);
    }
}
