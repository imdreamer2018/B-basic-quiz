package com.thoughtworks.basic.quiz.briefing.demo.service;

import com.thoughtworks.basic.quiz.briefing.demo.dto.User;
import com.thoughtworks.basic.quiz.briefing.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User userRequest) {
        userRequest.setId(userRepository.getMaxUserId());
        userRepository.setMaxStudentId();
        userRepository.save(userRequest);
        return userRequest;
    }
}
