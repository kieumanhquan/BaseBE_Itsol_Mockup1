package com.itsol.recruit.repository;

import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.repoext.UserRepositoryExt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryExt {

    @Modifying
    @Query("update Users u set u.password = :password where u.email = :email")
    int updateUserPassword(@Param("email") String email,
                           @Param("password") String password);

    User findByUserName(String userName);

    @Query("select u from Users u where u.email = :email")
    User findByEmail(String email);
}
