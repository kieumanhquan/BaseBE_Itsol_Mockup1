package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.ReviewTransfer;
import com.itsol.recruit.service.ReviewTranferService;
import com.itsol.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class ReviewTransferController {
    @Autowired
    private ReviewTranferService reviewTranferService ;

    @GetMapping("review-transfer")
    public ResponseEntity<List<ReviewTransfer>> findAll(){
        try {
            return ResponseEntity.ok(reviewTranferService.findAll());

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("review-transfer")
    public ResponseEntity<ReviewTransfer> createReviewTransfer(@RequestBody ReviewTransfer reviewTransfer){
        try {
            return ResponseEntity.ok(reviewTranferService.create(reviewTransfer));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping("review-transfer/{id}")
    public ResponseEntity<ReviewTransfer> updateReviewTransfer(@PathVariable("id") Integer id,@RequestBody ReviewTransfer reviewTransfer){
        try {
            return ResponseEntity.ok(reviewTranferService.update(reviewTransfer));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
