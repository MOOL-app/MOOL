package com.mool.app.dto;

import com.mool.app.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class userSignupDto {
    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNum;
    private LocalDate birthDate;

    @Builder
    public userSignupDto(String name, String email, String password, String address, String phoneNum, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNum = phoneNum;
        this.birthDate = birthDate;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .address(address)
                .phoneNum(phoneNum)
                .birthDate(birthDate)
                .build();
    }
}
