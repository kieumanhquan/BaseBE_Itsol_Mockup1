package com.itsol.recruit.service;

import com.itsol.recruit.entity.Unit;
import com.itsol.recruit.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface UserService {

    User save(User user);

    User findById(Long id);

    User update(User user);

    List<User> findAll();

    User findUserByUserName(String username);

    User findUserByCCCD(String cccd);

    User findUserByEmail(String email);

    User findUserByPhoneNumber(String phoneNumber);

    Page<User> sortByKey(Pageable pageable,
                         String name,
                         String email,
                         String literacy,
                         String position,
                         Long salary,
                         Date birthDay,
                         Unit unit,
                         Unit unitDm);
}
