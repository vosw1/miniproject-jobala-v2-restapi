package com.example.jobala.apply;

import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala._user.SessionUser;
import com.example.jobala._user.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplyController {

    private final HttpSession session;
    private final ApplyService applyService;

    // 기업 및 개인 - 지원 상태 업데이트 기존 기업 개인으로 구분되어 있던 것을 합침
    @PutMapping("/api/applies")
    public ResponseEntity<?> updateApplicationStatus(@Valid @RequestBody ApplyRequest.ApplyStatusUpdateRequestDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ApplyResponse.StatusUpdateDTO respDTO = applyService.statusUpdate(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 개인 - 이력서 지원하기
    @PostMapping("/api/applies")
    public ResponseEntity<?> apply(@Valid @RequestBody ApplyRequest.ApplyRequestDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ApplyResponse.ApplicationDTO respDTO = applyService.saveAfterApply(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 기업 및 개인 - 포지션 제안 현황보기
    @GetMapping("/api/applies/positionForm")
    public ResponseEntity<?> positionForm() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        if (sessionUser.getRole() == 0) { // 개인이 보는 포지션 제안 현황
            List<ApplyResponse.GuestPositionDTO> respDTO = applyService.findPositionGuestByUserId(sessionUser.getId());
            return ResponseEntity.ok(new ApiUtil<>(respDTO));
        } else { // 기업이 포지션 제안한 현황
            List<ApplyResponse.CompPositionDTO> respDTO = applyService.findPositionCompByUserId(sessionUser.getId());
            return ResponseEntity.ok(new ApiUtil<>(respDTO));
        }
    }

    // 기업 및 개인 - 이력서 지원 현황보기
    @GetMapping("/api/applies/applyForm")
    public ResponseEntity<?> applyForm() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        if (sessionUser.getRole() == 0) { // 개인이 지원한 이력서 현황
            List<ApplyResponse.GuestApplyDTO> respDTO = applyService.findApplyGuestByUserId(sessionUser.getId());
            return ResponseEntity.ok(new ApiUtil<>(respDTO));
        } else { // 기업이 보는 지원자 현황
            List<ApplyResponse.CompApplyDTO> respDTO = applyService.findApplyCompByUserId(sessionUser.getId());
            return ResponseEntity.ok(new ApiUtil<>(respDTO));
        }
    }
}
