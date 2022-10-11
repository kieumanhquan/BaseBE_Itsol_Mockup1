package com.itsol.recruit.service;

import com.itsol.recruit.entity.User;

import java.util.List;

public interface UserService {

    public User findById(Long id);

    public User findUserByEmail(String email);

    public List<User> findAll();

    int updateUserPassword(String email,String password);

    public User updateUser(User user);
}
