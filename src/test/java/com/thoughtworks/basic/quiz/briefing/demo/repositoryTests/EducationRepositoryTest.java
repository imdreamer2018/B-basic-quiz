package com.thoughtworks.basic.quiz.briefing.demo.repositoryTests;

import com.thoughtworks.basic.quiz.briefing.demo.dto.Education;
import com.thoughtworks.basic.quiz.briefing.demo.dto.User;
import com.thoughtworks.basic.quiz.briefing.demo.repository.EducationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class EducationRepositoryTest {

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void should_return_user_education_when_user_id_is_exists() {
        User user = User.builder()
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build();
        Education education = Education.builder()
                .title("mock title")
                .year(2020)
                .description("mock description")
                .user(user)
                .build();
        testEntityManager.persistAndFlush(user);
        testEntityManager.persistAndFlush(education);
        List<Education> userEducations = educationRepository.findByUserId(1L);
        assertThat(userEducations.get(0)).isEqualTo(education);
    }
}
