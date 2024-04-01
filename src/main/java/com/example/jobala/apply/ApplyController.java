package com.example.jobala.apply;


import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala._user.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;
@RestController
@RequiredArgsConstructor
public class ApplyController {

    private final HttpSession session;
    private final ApplyService applyService;
    private final ApplyQueryRepository applyQueryRepository;

    // 기업 및 개인 - 지원 상태 업데이트 기존 기업 개인으로 구분되어 있던 것을 합침
    @PostMapping("/api/applies/applyStatus/update")
    public ResponseEntity<ApiUtil<ApplyResponse.StatusUpdateDTO>> updateApplicationStatus(@RequestBody ApplyResponse.ApplyStatusUpdateRequestDTO requestDTO) {
        ApplyResponse.StatusUpdateDTO respDTO = applyService.statusUpdate(requestDTO.getApplyId(), requestDTO.getStatus());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 개인 - 이력서 지원하기
    @PostMapping("/api/applies/applys")
    public ResponseEntity<ApiUtil<ApplyResponse.ApplicationDTO>> apply(@RequestBody ApplyRequest.ApplyRequestDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ApplyResponse.ApplicationDTO respDTO = applyService.saveAfterApply(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 기업 및 개인 - 포지션 제안 현황보기
    @GetMapping("/api/applies/positionForm")
    public ResponseEntity<ApiUtil<?>> positionForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        return applyService.findPositionFormByUserId(sessionUser);
    }

    // 기업 및 개인 - 이력서 지원 현황보기
    @GetMapping("/api/applies/applyForm")
    public ResponseEntity<ApiUtil<?>> applyForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<ApplyResponse.GuestApplyDTO> respDTO = applyService.findApplyGuestByUserId(sessionUser.getId(), sessionUser.getRole());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}