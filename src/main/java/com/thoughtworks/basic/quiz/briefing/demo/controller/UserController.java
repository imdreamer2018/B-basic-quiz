package com.thoughtworks.basic.quiz.briefing.demo.controller;

import com.thoughtworks.basic.quiz.briefing.demo.dto.Education;
import com.thoughtworks.basic.quiz.briefing.demo.dto.User;
import com.thoughtworks.basic.quiz.briefing.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Valid User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/{id}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public Education createUserEducation(
            @PathVariable long id,
            @RequestBody Education education) {
        return userService.createEducation(id, education);
    }

    @GetMapping("/{id}/educations")
    public List<Education> getUserEducations(@PathVariable long id) {
        return userService.getEducationsByUserId(id);
    }
}
