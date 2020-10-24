package com.thoughtworks.basic.quiz.briefing.demo.service;

import com.thoughtworks.basic.quiz.briefing.demo.dto.Education;
import com.thoughtworks.basic.quiz.briefing.demo.dto.User;
import com.thoughtworks.basic.quiz.briefing.demo.exception.ResourceNotFoundException;
import com.thoughtworks.basic.quiz.briefing.demo.repository.EducationRepository;
import com.thoughtworks.basic.quiz.briefing.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final EducationRepository educationRepository;

    public UserService(UserRepository userRepository, EducationRepository educationRepository) {
        this.userRepository = userRepository;
        this.educationRepository = educationRepository;
    }

    public User createUser(User userRequest) {
        userRepository.save(userRequest);
        return userRequest;
    }

    public User getUserById(long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElseThrow(() -> new ResourceNotFoundException(
                "can not find basic info of user with id is " + userId));
    }

    public Education createEducation(long userId, Education education) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new ResourceNotFoundException(
                "can not find basic info of user with id is " + userId));
        education.setUser(user);
        educationRepository.save(education);
        return education;
    }

    public List<Education> getEducationsByUserId(long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException(
                    "can not find basic info of user with id is " + userId);
        }
        return educationRepository.findByUserId(userId);
    }
}
