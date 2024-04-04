package com.example.jobala.scrap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScrapJPARepository extends JpaRepository<Scrap, Integer> {

    // 기업이 인재 스크랩
    Optional<Scrap> findCompScrapByResumeIdAndUserId(@Param("resumeId") int resumeId, @Param("userId") int userId);

    // 개인이 채용공고 스크랩
    Optional<Scrap> findGuestScrapByJobopenIdAndUserId(@Param("jobopenId") int jobopenId, @Param("userId") int userId);

}
