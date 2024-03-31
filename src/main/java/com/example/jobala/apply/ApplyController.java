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
    public ResponseEntity<ApiUtil<String>> updateApplicationStatus(@RequestParam("applyId") Integer applyId, @RequestParam("status") String status) {
        applyService.statusUpdate(applyId, status);
        return ResponseEntity.ok(new ApiUtil("Status updated successfully"));
    }

    // 개인 - 이력서 지원하기
    @PostMapping("/api/applies/applys")
    public ResponseEntity<ApiUtil<String>> apply(@RequestBody ApplyRequest.ApplyRequestDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        applyService.saveAfterApply(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil("Application submitted successfully"));
    }

    // 기업 및 개인 - 포지션 제안 현황보기
    @GetMapping("/api/applies/positionForm")
    public ResponseEntity<ApiUtil<?>> positionForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser.getRole() == 1) {
            List<ApplyResponse.CompPositionDTO> respDTO = applyService.findApplyCompByUserId(sessionUser.getId());
            return ResponseEntity.ok(new ApiUtil(respDTO));
        } else {
            List<ApplyResponse.GuestPositionDTO> respDTO = applyQueryRepository.findJopOpenByUserId(sessionUser.getId());
            return ResponseEntity.ok(new ApiUtil(respDTO));
        }
    }

    // 기업 및 개인 - 이력서 지원 현황보기
    @GetMapping("/api/applies/applyForm")
    public ResponseEntity<ApiUtil<?>> applyForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser.getRole() == 1) {
            List<ApplyResponse.CompApplyDTO> respDTO = applyQueryRepository.findByUserId(sessionUser.getId(), sessionUser.getRole());
            return ResponseEntity.ok(new ApiUtil(respDTO));
        } else {
            List<ApplyResponse.GuestApplyDTO> respDTO = applyService.findApplyGuestByUserId(sessionUser.getId());
            return ResponseEntity.ok(new ApiUtil(respDTO));
        }
    }
}