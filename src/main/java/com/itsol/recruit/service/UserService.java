package com.itsol.recruit.service;

import com.itsol.recruit.entity.Contract;
import com.itsol.recruit.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findById(Long id);


    User update(User user);


    List<User> findAll();

    User findUserByUserName(String username);
}
