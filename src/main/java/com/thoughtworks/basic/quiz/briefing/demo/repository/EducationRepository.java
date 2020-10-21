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
        this.maxEducationId++;
    }

    public void deleteAll() {
        educationMap.clear();
    }

    public void save(Education education) {
        educationMap.put(education.getId(), education);
    }

    public List<Education> findByUserId(long userId) {
        List<Education> educations = new ArrayList<>();
        for (Map.Entry<Long, Education> education: educationMap.entrySet()) {
            if (education.getValue().getUserId() == userId)
                educations.add(education.getValue());
        }
        return educations;
    }
}
