package com.thoughtworks.basic.quiz.briefing.demo.serviceTests;

import com.thoughtworks.basic.quiz.briefing.demo.dto.Education;
import com.thoughtworks.basic.quiz.briefing.demo.dto.User;
import com.thoughtworks.basic.quiz.briefing.demo.exception.ResourceNotFoundException;
import com.thoughtworks.basic.quiz.briefing.demo.repository.EducationRepository;
import com.thoughtworks.basic.quiz.briefing.demo.repository.UserRepository;
import com.thoughtworks.basic.quiz.briefing.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
class UserServiceTest {

    private UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    EducationRepository educationRepository;

    private User user;

    private Education education;

    @BeforeEach
    void setup() {
        initMocks(this);
        userService = new UserService(userRepository, educationRepository);
        user = User.builder()
                .id(1)
                .name("mock name")
                .age(18)
                .avatar("https://inews.gtimg.com/newsapp_match/0/3581582328/0")
                .description("mock user description")
                .build();

        education = Education.builder()
                .id(1)
                .year(2020)
                .title("mock education title")
                .description("mock education description")
                .userId(1)
                .build();

    }

    @Test
    void should_return_user_info_when_create_user_success() {
        User newUser = userService.createUser(user);
        verify(userRepository).save(user);
        assertEquals("mock name", newUser.getName());
    }

    @Test
    void should_return_user_info_when_get_user_by_id_success() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User userById = userService.getUserById(user.getId());
        assertEquals("mock name", userById.getName());
    }

    @Test
    void should_throw_exception_when_get_user_by_id_not_found() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(user.getId()));
        assertEquals("can not find basic info of user with id is " + user.getId(), exception.getMessage());
    }

    @Test
    void should_return_user_education_info_when_create_user_education_success() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Education newEducation = userService.createEducation(user.getId(), education);
        verify(educationRepository).save(education);
        assertEquals("mock education title", newEducation.getTitle());
    }

    @Test
    void should_return_user_education_info_when_get_user_education_by_user_id_success() {
        List<Education> educations = new ArrayList<>();
        educations.add(education);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(educationRepository.findByUserId(user.getId())).thenReturn(educations);
        List<Education> educationList = userService.getEducationsByUserId(user.getId());
        assertEquals("mock education title", educationList.get(0).getTitle());
    }

    @Test
    void should_throw_exception_when_get_education_by_user_id_not_found() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> userService.getEducationsByUserId(user.getId()));
        assertEquals("can not find basic info of user with id is " + user.getId(), exception.getMessage());
    }
}
