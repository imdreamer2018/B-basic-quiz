package com.thoughtworks.basic.quiz.briefing.demo.repositoryTests;

import com.thoughtworks.basic.quiz.briefing.demo.dto.User;
import com.thoughtworks.basic.quiz.briefing.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void should_return_user_when_id_exists() {
        entityManager.persistAndFlush(User.builder()
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build());

        Optional<User> found = userRepository.findById(1L);

        assertThat(found.isPresent()).isTrue();
        assertThat(found.get()).isEqualTo(User.builder()
                .id(1L)
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build());
    }
}
