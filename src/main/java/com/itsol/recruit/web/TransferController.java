package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.Transfer;
import com.itsol.recruit.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class TransferController {
    @Autowired
    private TransferService transferService;

    @GetMapping("transfer")
    public ResponseEntity<List<Transfer>> findAll(){
        try {
            return ResponseEntity.ok(transferService.getAllTransfer());

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("transfer")
    public ResponseEntity<Transfer> createTransfer(@RequestBody Transfer transfer){
        try {
            return ResponseEntity.ok(transferService.createTranfer(transfer));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping("transfer/{id}")
    public ResponseEntity<Transfer> updateTransfer(@PathVariable("id") Integer id, @RequestBody Transfer transfer){
        try {
            return ResponseEntity.ok(transferService.updateTransfer(transfer));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("transfer/{id}")
    public void delete(@RequestParam("id") Integer id){
        transferService.deleteById(id);
    }
    @GetMapping("transfer/{id}")
    public ResponseEntity<Transfer> getTransferById(@RequestParam("id") Integer id){
        try {
            return ResponseEntity.ok(transferService.getById(id));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
