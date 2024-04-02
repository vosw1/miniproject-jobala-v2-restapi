package com.example.jobala.apply;


import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala._user.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;
@RestController
@RequiredArgsConstructor
public class ApplyController {

    private final HttpSession session;
    private final ApplyService applyService;

    // 기업 및 개인 - 지원 상태 업데이트 기존 기업 개인으로 구분되어 있던 것을 합침
    @PutMapping("/api/applies")
    public ResponseEntity<?> updateApplicationStatus(@Valid @RequestBody ApplyRequest.ApplyStatusUpdateRequestDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ApplyResponse.StatusUpdateDTO respDTO = applyService.statusUpdate(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 개인 - 이력서, 포지션제안 지원하기
    @PostMapping("/api/applies")
    public ResponseEntity<?> apply(@Valid @RequestBody ApplyRequest.ApplyRequestDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ApplyResponse.ApplicationDTO respDTO= applyService.saveAfterApply(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 기업 및 개인 - 포지션 제안 현황보기
    @GetMapping("/api/applies/positionForm")
    public ResponseEntity<ApiUtil<?>> positionForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<?> respDTO = applyService.findPositionByUserId(sessionUser.getId(), sessionUser.getRole());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 기업 및 개인 - 이력서 지원 현황보기
    @GetMapping("/api/applies/applyForm")
    public ResponseEntity<ApiUtil<?>> applyForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<?> respDTO = applyService.findApplyByUserId(sessionUser.getId(), sessionUser.getRole());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}