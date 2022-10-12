package com.itsol.recruit.service;

import com.itsol.recruit.entity.ReviewTransfer;

import java.util.List;

public interface ReviewTranferService {
    public List<ReviewTransfer> findAll();
    public ReviewTransfer create(ReviewTransfer reviewTransfer);
    public ReviewTransfer update(ReviewTransfer reviewTransfer);
    public ReviewTransfer getById(Integer id);
    public void deleteById(Integer id);


}
