package com.itsol.recruit.service;

import com.itsol.recruit.entity.Contract;
import com.itsol.recruit.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findById(Long id);


    User update(User user);


    List<User> findAll();

<<<<<<< HEAD
=======
    public User updateUser(User user);

>>>>>>> f21ab92c26c41f019d00a8e171baf185775f7e50
    User findUserByUserName(String username);
}
