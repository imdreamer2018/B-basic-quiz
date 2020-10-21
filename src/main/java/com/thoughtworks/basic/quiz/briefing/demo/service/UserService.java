package com.thoughtworks.basic.quiz.briefing.demo.service;

import com.thoughtworks.basic.quiz.briefing.demo.dto.User;
import com.thoughtworks.basic.quiz.briefing.demo.exception.ResourceNotFoundException;
import com.thoughtworks.basic.quiz.briefing.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public User getUserById(long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElseThrow(() -> new ResourceNotFoundException(
                "can not find basic info of user with id is" + userId));
    }
}
