package com.example.jobala.apply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplyJPARepository extends JpaRepository<Apply, Integer> {

    // 개인이 지원한 이력서 현황
    @Query("select new com.example.jobala.apply.ApplyResponse$GuestApplyDTO(a) from Apply a where a.user.id = :userId")
    List<ApplyResponse.GuestApplyDTO> findApplyGuestByUserId(@Param("userId") int userId);

    // 기업이 포지션 제안한 현황
    @Query("select new com.example.jobala.apply.ApplyResponse$CompPositionDTO(a) from Apply a where a.user.id = :userId")
    List<ApplyResponse.CompPositionDTO> findPositionCompByUserId(@Param("userId") int userId);

    // 기업이 보는 지원자 현황
    @Query("select new com.example.jobala.apply.ApplyResponse$CompApplyDTO(a) from Apply a where a.jobopen.user.id = :userId and a.role = 0")
    List<ApplyResponse.CompApplyDTO> findApplyCompByUserId(@Param("userId") int userId);

    // 개인이 보는 포지션 제안 현황
    @Query("select new com.example.jobala.apply.ApplyResponse$GuestPositionDTO(a) from Apply a where a.resume.user.id = :userId and a.role = 1")
    List<ApplyResponse.GuestPositionDTO> findPositionGuestByUserId(@Param("userId") int userId);

    // 지원한 공고의 수 세기
    @Query("SELECT COUNT(a) FROM Apply a WHERE a.jobopen.id = :jobopenId AND a.role = 0 AND a.state = '검토중'")
    int countJobopenApplyById(@Param("jobopenId") int jobopenId);
}
