package com.example.jobala.resume;

import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala._user.SessionUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ResumeController {

    private final HttpSession session;
    private final ResumeService resumeService;

    //이력서 업데이트
    @PutMapping("/api/guest/resumes/{id}")  // 주소 수정 필요
    public ResponseEntity<?> update(@Valid @PathVariable Integer id, @RequestBody ResumeRequest.UpdateDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ResumeResponse.UpdateDTO respDTO = resumeService.resumeUpdate(id, reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    //이력서 상세보기
    @GetMapping("/api/guest/resumes/{id}")
    public ResponseEntity<?> detailForm(@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ResumeResponse.DetailDTO respDTO = resumeService.resumeFindById(id, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    //이력서 등록
    @PostMapping("/api/guest/resumes")  // 주소 수정 필요
    public ResponseEntity<?> save(@Valid @RequestBody ResumeRequest.SaveDTO resumeSaveDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ResumeResponse.ASaveDTO respDTO = resumeService.resumeSave(resumeSaveDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    //이력서 삭제
    @DeleteMapping("/api/guest/resumes/{id}")  // 주소 수정 필요
    public ResponseEntity<?> delete(@PathVariable int id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        resumeService.resumeDelete(id, sessionUser);
        return ResponseEntity.ok(new ApiUtil(null));
    }
}