package com.itsol.recruit.service;

import com.itsol.recruit.entity.Contract;
import com.itsol.recruit.entity.User;

import java.util.List;

public interface ContractService {
    public List<Contract> getAllContract();
    public Contract finById(Integer id);
    public List<Contract> finByUser(User user);
    public Contract createContract(Contract contract);
    public Contract updateContract(Contract contract);
}
