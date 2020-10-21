package com.thoughtworks.basic.quiz.briefing.demo.controller;

import com.thoughtworks.basic.quiz.briefing.demo.dto.Education;
import com.thoughtworks.basic.quiz.briefing.demo.dto.User;
import com.thoughtworks.basic.quiz.briefing.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
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
    public User getUserById(
            @NotNull(message = "user id can not be null")
            @DecimalMin(value = "1", message = "user id muse be number and greater than 1")
            @PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/{id}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public Education createUserEducation(
            @NotNull(message = "user id can not be null")
            @DecimalMin(value = "1", message = "user id muse be number and greater than 1")
            @PathVariable long id,
            @RequestBody @Valid Education education) {
        return userService.createEducation(id, education);
    }

    @GetMapping("/{id}/educations")
    public List<Education> getUserEducations(
            @NotNull(message = "user id can not be null")
            @DecimalMin(value = "1", message = "user id muse be number and greater than 1")
            @PathVariable long id) {
        return userService.getEducationsByUserId(id);
    }
}
