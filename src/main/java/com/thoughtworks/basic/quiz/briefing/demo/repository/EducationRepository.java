package com.thoughtworks.basic.quiz.briefing.demo.repository;

import com.thoughtworks.basic.quiz.briefing.demo.dto.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {

    @Query(nativeQuery = true, value = "select * from Education e where e.user_id = :userId")
    @Transactional
    List<Education> findByUserId(@Param("userId") Long userId);
}
