package com.thoughtworks.basic.quiz.briefing.demo.controllerTests;

import com.thoughtworks.basic.quiz.briefing.demo.controller.UserController;
import com.thoughtworks.basic.quiz.briefing.demo.dto.Education;
import com.thoughtworks.basic.quiz.briefing.demo.dto.User;
import com.thoughtworks.basic.quiz.briefing.demo.exception.ResourceNotFoundException;
import com.thoughtworks.basic.quiz.briefing.demo.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureJsonTesters
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private JacksonTester<User> userJson;

    @Autowired
    private JacksonTester<Education> educationJson;

    private User user;

    private Education education;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .name("yangqian")
                .age(18)
                .avatar("https://inews.gtimg.com/newsapp_match/0/3581582328/0")
                .description("description mock")
                .build();
        education = Education.builder()
                .year(2020)
                .title("mock education title")
                .description("mock education description")
                .user(user)
                .build();
    }

    @AfterEach
    void afterEach() {
        Mockito.reset(userService);
    }

    @Test
    void should_return_user_info_when_get_create_user_success() throws Exception {
        when(userService.createUser(user)).thenReturn(user);
        mockMvc.perform(post("/users")
                .content(userJson.write(user).getJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("yangqian")));
        verify(userService).createUser(user);
    }

    @Test
    void should_return_user_info_when_get_user_by_id() throws Exception {
        when(userService.getUserById(1L)).thenReturn(user);
        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("yangqian")));
        verify(userService).getUserById(1L);
    }

    @Test
    void should_return_exception_when_get_user_by_id_not_found() throws Exception {
        when(userService.getUserById(1L)).thenThrow(new ResourceNotFoundException("can not find basic info of user with id is 1"));
        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("can not find basic info of user with id is 1")));
        verify(userService).getUserById(1L);
    }

    @Test
    void should_return_education_info_when_create_user_education_by_user_id_success() throws Exception {
        when(userService.createEducation(1L, education)).thenReturn(education);
        mockMvc.perform(post("/users/{id}/educations", 1L)
                .content(educationJson.write(education).getJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(userService).createEducation(1L, education);
    }

    @Test
    void should_return_exception_when_create_user_education_by_user_id_not_found() throws Exception {
        when(userService.createEducation(1L, education)).thenThrow(new ResourceNotFoundException(
                "can not find basic info of user with id is 1"));
        mockMvc.perform(post("/users/{id}/educations", 1L)
                .content(educationJson.write(education).getJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("can not find basic info of user with id is 1")));
        verify(userService).createEducation(1L, education);
    }

    @Test
    void should_return_educations_when_get_user_educations_success() throws Exception {
        List<Education> educations = new ArrayList<>();
        educations.add(education);
        when(userService.getEducationsByUserId(1L)).thenReturn(educations);
        mockMvc.perform(get("/users/{id}/educations", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title", is("mock education title")));
    }
}
