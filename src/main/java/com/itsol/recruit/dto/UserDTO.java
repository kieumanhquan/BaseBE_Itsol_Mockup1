package com.itsol.recruit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itsol.recruit.entity.Unit;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class UserDTO {

    String fullName;

    String email;

    String userName;

    String password;

    String phoneNumber;

    String homeTown;

    String avatarName;

    String gender;

    String newPassword;

    Date birthDay;

    String name;

    String literacy;

    String position;

    Long salary;

    @JsonFormat(pattern = "yyyy-MM-dd")

    Unit unit;
}
