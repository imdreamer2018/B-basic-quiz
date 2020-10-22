package com.thoughtworks.basic.quiz.briefing.demo.repository;

import com.thoughtworks.basic.quiz.briefing.demo.dto.Education;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EducationRepository {

    private Map<Long, Education> educationMap = new HashMap<>();

    private Long maxEducationId = 1L;

    public Long getMaxEducationIdId() {
        return maxEducationId;
    }

    public void setMaxEducationId() {
        // TODO GTB-3: - ++操作线程不安全，可以了解下AtomicLong
        this.maxEducationId++;
    }

    public void deleteAll() {
        educationMap.clear();
    }

    public void save(Education education) {
        educationMap.put(education.getId(), education);
    }

    public List<Education> findByUserId(long userId) {
        // TODO GTB-3: - 下面的代码可以使用Java8 Stream简化
        List<Education> educations = new ArrayList<>();
        for (Map.Entry<Long, Education> education: educationMap.entrySet()) {
            if (education.getValue().getUserId() == userId)
                educations.add(education.getValue());
        }
        return educations;
    }
}
