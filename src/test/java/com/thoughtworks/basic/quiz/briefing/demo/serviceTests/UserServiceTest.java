package com.thoughtworks.basic.quiz.briefing.demo.serviceTests;

import com.thoughtworks.basic.quiz.briefing.demo.dto.User;
import com.thoughtworks.basic.quiz.briefing.demo.repository.UserRepository;
import com.thoughtworks.basic.quiz.briefing.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
class UserServiceTest {

    private UserService userService;

    @Mock
    UserRepository userRepository;

    private User user;

    @BeforeEach
    void setup() {
        initMocks(this);
        userService = new UserService(userRepository);
        user = User.builder()
                .name("yangqian")
                .age(18)
                .avatar("https://inews.gtimg.com/newsapp_match/0/3581582328/0")
                .description("description mock")
                .build();
    }

    @Test
    void should_return_user_info_when_create_user_success() {
        User newUser = userService.createUser(user);
        verify(userRepository).save(user);
        assertEquals("yangqian", newUser.getName());
    }
}
