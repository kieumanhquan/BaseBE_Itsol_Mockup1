package com.itsol.recruit.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity(name = "Otp")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_OTP_ID")
    @SequenceGenerator(name = "OTP_UEQ", sequenceName = "OTP_SEQ", allocationSize = 1)
    Integer id;

    @Column(name = "CODE")
    Integer code;

    @Column(name = "USER_ID")
    Long userid;

    @Column(name = "ISSUE_AT")
    Long issue_At;

    @Column(name = "STATUS")
    Integer status;
}
