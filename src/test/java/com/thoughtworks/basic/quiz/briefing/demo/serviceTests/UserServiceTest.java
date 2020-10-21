package com.thoughtworks.basic.quiz.briefing.demo.serviceTests;

import com.thoughtworks.basic.quiz.briefing.demo.dto.User;
import com.thoughtworks.basic.quiz.briefing.demo.exception.ResourceNotFoundException;
import com.thoughtworks.basic.quiz.briefing.demo.repository.UserRepository;
import com.thoughtworks.basic.quiz.briefing.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

    @Test
    void should_return_user_info_when_get_user_by_id_success() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User userById = userService.getUserById(user.getId());
        assertEquals("yangqian", userById.getName());
    }

    @Test
    void should_throw_exception_when_get_user_by_id_not_found() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(user.getId()));
        assertEquals("can not find basic info of user with id is" + user.getId(), exception.getMessage());
    }
}
