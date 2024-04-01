package com.example.jobala.apply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplyJPARepository extends JpaRepository<Apply, Integer> {

    @Query("select new com.example.jobala.apply.ApplyResponse$GuestPositionDTO(a) from Apply a where a.user.id = :userId")
    List<ApplyResponse.GuestPositionDTO> findPositionGuestByUserId(@Param("userId") int userId);
    @Query("select new com.example.jobala.apply.ApplyResponse$GuestApplyDTO(a) from Apply a where a.user.id = :userId")
    List<ApplyResponse.GuestApplyDTO> findApplyGuestByUserId(@Param("userId") int userId);
    @Query("SELECT COUNT(a) FROM Apply a WHERE a.jobopen.id = :jobopenId AND a.role = 0 AND a.state = '검토중'")
    int countJobopenApplyById(@Param("jobopenId") int jobopenId);
}
