package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.ReviewTransfer;
import com.itsol.recruit.repository.ReviewTransferRepository;
import com.itsol.recruit.service.ReviewTranferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewTransferServiceImpl implements ReviewTranferService {
    @Autowired
    private ReviewTransferRepository reviewTransferRepo;
    @Override
    public List<ReviewTransfer> findAll() {
        return reviewTransferRepo.findAll();
    }

    @Override
    public ReviewTransfer create(ReviewTransfer reviewTransfer) {
        return reviewTransferRepo.save(reviewTransfer);
    }

    @Override
    public ReviewTransfer update(ReviewTransfer reviewTransfer) {
        return reviewTransferRepo.save(reviewTransfer);
    }

    @Override
    public ReviewTransfer getById(Integer id) {
        return reviewTransferRepo.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        reviewTransferRepo.deleteById(id);
    }
}
