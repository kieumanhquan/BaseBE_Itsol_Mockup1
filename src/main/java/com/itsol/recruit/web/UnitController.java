package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.Unit;
import com.itsol.recruit.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class UnitController {
    @Autowired
    private UnitService unitService;

    @GetMapping("unit")
    public ResponseEntity<List<Unit>> findAll(){
        try {
            return ResponseEntity.ok(unitService.finAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/unit/not-join-user/{id}")
    public ResponseEntity<List<Unit>> findUnitNotJoinUser(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(unitService.findUnitNotJoinUser(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
