package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Unit;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findByUserName(username);
    }
    //chung
    @Override
    public User findDMByUnit(Unit unit) {
        return userRepository.findDMbyUnit(unit);
    }

    @Override
    public User findDMByUnitId(Integer id) {
        return userRepository.findDMbyUnitId(id);
    }

    @Override
    public User findUserByCCCD(String cccd) {
        return userRepository.findByCCCD(cccd);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Page<User> sortByKey(Pageable pageable, String fullName, String email, String literacy,
                                String position, Long salary, Date birthDay, Unit unit,   Unit unitDm) {
        return userRepository.findByKey(pageable,fullName,email,literacy,position,salary,birthDay,unit,unitDm);
    }

    //trangcode
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    //
    public User updateUser(User user) {

        return userRepository.save(user);
    }


}
