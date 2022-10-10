package com.itsol.recruit.service;

public interface EmailSenderService {
    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body);
}
