package com.thoughtworks.basic.quiz.briefing.demo.serviceTests;

import com.thoughtworks.basic.quiz.briefing.demo.repository.UserRepository;
import com.thoughtworks.basic.quiz.briefing.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
public class UserServiceTest {

    private UserService userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        initMocks(this);
        userService = new UserService(userRepository);
    }
}
