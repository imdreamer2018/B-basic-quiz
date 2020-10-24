package com.thoughtworks.basic.quiz.briefing.demo.repository;

import com.thoughtworks.basic.quiz.briefing.demo.dto.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
