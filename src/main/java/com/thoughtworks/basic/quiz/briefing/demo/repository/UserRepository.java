package com.thoughtworks.basic.quiz.briefing.demo.repository;

import com.thoughtworks.basic.quiz.briefing.demo.dto.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    private Map<Long, User> userMap = new HashMap<>();

    private Long maxUserId = 1L;

    public Long getMaxUserId() {
        return maxUserId;
    }

    public void setMaxStudentId(Long maxUserId) {
        this.maxUserId = maxUserId;
    }

    public void deleteAll() {
        userMap.clear();
    }

    public void save(User user) {
        userMap.put(user.getId(), user);
    }

    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(userMap.get(userId));
    }

    public List<User> findAll() {
        List<User> students = new ArrayList<>();
        for (Map.Entry<Long, User> user: userMap.entrySet()) {
            students.add(user.getValue());
        }
        return students;
    }
}
