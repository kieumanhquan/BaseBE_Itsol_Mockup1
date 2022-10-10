package com.itsol.recruit.service;

import com.itsol.recruit.entity.Otp;

import java.util.List;

public interface OtpService {
    public List<Otp> getAllOtp();

    public Otp save(Otp otp);
}
