package com.mool.app.controller;

import com.mool.app.dto.LoginRequestDto;
import com.mool.app.dto.UserSignupDto;
import com.mool.app.entity.User;
import com.mool.app.jwt.JwtTokenUtil;
import com.mool.app.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    @Value("${jwt.secret}")
    private String secretKey;

//    @PostMapping("/signup")
//    public Long signup(@RequestBody  userSignupDto dto) {
//
//    }
    @PostMapping("/join")
    public Long join(@RequestBody UserSignupDto dto) {
        return userService.join(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDto dto) {
        User user = userService.login(dto);

        if (user == null) {
            return ResponseEntity.badRequest().body("email 또는 비밀번호가 틀렸습니다.");
        }

        long expireTimeMs = 1000 * 60 * 60 * 8; // Token 유효시간 = 8 시간
        String jwtToken = JwtTokenUtil.createToken(dto.getEmail(), secretKey, expireTimeMs);


        //사용자 정보를 JSON 형태로 리턴
        Map<String, Object> response = new HashMap<>();
        response.put("userId", user.getId());
        response.put("token", jwtToken);
        response.put("email", user.getEmail());
        response.put("username", user.getName());
        response.put("nickname", user.getNickname());
        response.put("address", user.getAddress());
        response.put("birthDate", user.getBirthDate());
        response.put("phoneNum", user.getPhoneNum());
        response.put("useYn", user.getUseYn());
        response.put("score", user.getScore());
        response.put("role", user.getRole());

        return ResponseEntity.ok(response);
    }
}
