package com.mool.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity{

    @GeneratedValue @Id
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String name;

    private String nickname;

    private String password;

    private String address;

    private int useYn;

    private LocalDate birthDate;

    private String phoneNum;

    private Long score;

    @Builder
    public User(String email, String name, String nickname, String password,
                     String phoneNum, String address, LocalDate birthDate) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.phoneNum = phoneNum;
        this.address = address;
        this.useYn = 0;
        this.score = 0L;

    }
}
