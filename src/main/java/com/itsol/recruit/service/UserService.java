package com.itsol.recruit.service;

import com.itsol.recruit.entity.Contract;
import com.itsol.recruit.entity.User;

import java.util.List;

public interface UserService {

    public User findById(Long id);

    public User findUserByEmail(String email);

    public User update(User user);


    public List<User> findAll();

    int updateUserPasswordName(String userName,String password);

    int updateUserPassword(String email,String password);

    public User updateUser(User user);
}
