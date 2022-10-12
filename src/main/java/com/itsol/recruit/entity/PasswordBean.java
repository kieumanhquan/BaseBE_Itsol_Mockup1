package com.itsol.recruit.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class PasswordBean {
    @NotBlank
    @Length(min = 8)
    private String passwordOld;
    @NotBlank
    @Length(min = 8)
    private String passwordNew;
    @NotBlank
    @Length(min = 8)
    private String confirmPassword;
}
