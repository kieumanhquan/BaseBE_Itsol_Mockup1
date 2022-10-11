package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
    @Modifying
    @Query("select o from Otp o inner join  Users u on o.userid = u.id where u.email = :email")
    List<Otp> checkOtp(@Param("email") String email);

}
