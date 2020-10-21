package com.thoughtworks.basic.quiz.briefing.demo.controllerTests;

import com.thoughtworks.basic.quiz.briefing.demo.dto.Education;
import com.thoughtworks.basic.quiz.briefing.demo.dto.User;
import com.thoughtworks.basic.quiz.briefing.demo.repository.EducationRepository;
import com.thoughtworks.basic.quiz.briefing.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EducationRepository educationRepository;

    private User user;

    private Education education;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        user = User.builder()
                .id(1)
                .name("yangqian")
                .age(18)
                .avatar("https://inews.gtimg.com/newsapp_match/0/3581582328/0")
                .description("description mock")
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
    void should_return_user_info_when_get_create_user_success() throws Exception {
        String jsonValue =  "{\"name\":\"yangqian\", " +
                "\"age\": 18, " +
                "\"avatar\":\"https://inews.gtimg.com/newsapp_match/0/3581582328/0\", " +
                "\"description\":\"description mock\"}";
        mockMvc.perform(post("/users")
                .content(jsonValue).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("yangqian")));
    }

    @Test
    void should_return_user_info_when_get_user_by_id() throws Exception {
        userRepository.save(user);
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("yangqian")));
    }

    @Test
    void should_return_exception_when_get_user_by_id_not_found() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)));
    }

    @Test
    void should_return_education_info_when_create_user_education_by_user_id_success() throws Exception {
        userRepository.save(user);
        String jsonValue =  "{\"year\":2020, " +
                "\"title\":\"mock education title\", " +
                "\"description\":\"mock education description\"}";
        mockMvc.perform(post("/users/1/educations")
                .content(jsonValue).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("mock education title")));
    }

    @Test
    void should_return_exception_when_create_user_education_by_user_id_not_found() throws Exception {
        String jsonValue =  "{\"year\":2020, " +
                "\"title\":\"mock education title\", " +
                "\"description\":\"mock education description\"}";
        mockMvc.perform(post("/users/1/educations")
                .content(jsonValue).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_return_educations_when_get_user_educations_success() throws Exception {
        userRepository.save(user);
        educationRepository.save(education);
        mockMvc.perform(get("/users/1/educations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("mock education title")));
    }
}
