package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.Contract;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.service.ContractService;
import com.itsol.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class ContractController {
    @Autowired
    private ContractService contractService;
    @GetMapping("contract")
    public ResponseEntity<List<Contract>> finAll(){
        return ResponseEntity.ok().body(contractService.getAllContract());
    }
    @GetMapping("contract/{id}")
    public ResponseEntity<Contract> finById(@RequestParam("id") Integer id){
        return ResponseEntity.ok().body(contractService.finById(id));
    }
    @PostMapping("contract")
    public ResponseEntity<Contract> createContract(@RequestBody Contract contract){
       return  ResponseEntity.ok().body(contractService.createContract(contract));
    }
    @PutMapping("contract/{id}")
    public ResponseEntity<Contract> updateContract(@RequestParam("id") Integer id, @RequestBody Contract contract){
        return  ResponseEntity.ok().body(contractService.updateContract(contract));
    }
    @GetMapping("contract/{user}")
    public ResponseEntity<List<Contract>> findByUser(@RequestParam("user")User user){
        return  ResponseEntity.ok().body(contractService.finByUser(user));
    }
}
