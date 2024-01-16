package com.mool.app.repository.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

//    @Autowired
//    UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        //userRepository.deleteAll();
    }

    @Test
    public void 유저저장_불러오기() {

    }

}