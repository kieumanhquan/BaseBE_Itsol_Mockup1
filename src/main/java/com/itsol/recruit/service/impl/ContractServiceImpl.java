package com.itsol.recruit.service.impl;


import com.itsol.recruit.entity.Contract;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.ContractRepository;
import com.itsol.recruit.service.ContractService;
import com.itsol.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    ContractRepository contractRepo;
    @Override
    public List<Contract> getAllContract() {
        return contractRepo.findAll();
    }

    @Override
    public Contract finById(Integer id) {
        return contractRepo.findById(id).get();
    }

    @Override
    public List<Contract> finByUser(User user) {

        return contractRepo.findByUser(user);
    }

    @Override
    public Contract createContract(Contract contract) {
        return contractRepo.save(contract);
    }

    @Override
    public Contract updateContract(Contract contract) {
        return contractRepo.save(contract);
    }
}
