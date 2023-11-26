package com.mool.app.service.user;

import com.mool.app.dto.LoginRequestDto;
import com.mool.app.dto.UserSignupDto;
import com.mool.app.entity.User;
import com.mool.app.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User getLoginUserByLoginId(String loginId) {
        return userRepository.findByEmail(loginId).orElseThrow(() -> new IllegalStateException("해당 회원이 존재하지 않습니다."));
    }

    public User login(LoginRequestDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 에메일입니다."));

        String password = user.getPassword();

        if (!user.checkPassword(passwordEncoder, password)) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        List<String> roles = new ArrayList<>();
        roles.add(user.getRole());

        return user;

    }

    @Transactional
    public Long join(UserSignupDto dto) {
        if(userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }

        User user = userRepository.save(dto.toEntity());
        user.encodePassword(passwordEncoder);

        //user.addUserA
        return user.getId();
    }
}
