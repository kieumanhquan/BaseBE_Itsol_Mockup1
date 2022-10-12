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
    @Query("select o from Otp o where o.userid =?1 and o.code =?2")
    Otp findByUserId(Long userId, Integer code);

}
