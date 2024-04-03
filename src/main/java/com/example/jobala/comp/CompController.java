package com.example.jobala.comp;


import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala._user.SessionUser;
import com.example.jobala._user.UserJPARepository;
import com.example.jobala._user.UserResponse;
import com.example.jobala.apply.ApplyJPARepository;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.ResumeJPARepository;
import com.example.jobala.resume.ResumeResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompController {

    private final HttpSession session;
    private final CompQueryRepository compQueryRepository;
    private final ApplyJPARepository applyJPARepository;
    private final CompService compService;
    private final UserJPARepository userJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    private final CompJPARepository compJPARepository;

    //기업 -인재 이력서 검색하기
    @GetMapping("/api/comp/resumeSearch")
    public ResponseEntity<?> resumeSearch(@RequestParam(value = "skills", defaultValue = "") String skills, CompRequest.SearchDTO resDTO) {
        List<ResumeResponse.ScoutListDTO> respDTO = compService.searchResumes(skills, resDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // 기업 - 인재 명단 목록
    @GetMapping("/api/comp/scoutList")
    public ResponseEntity<?> scoutList() {
        List<ResumeResponse.ScoutListDTO> respDTO = compService.listAllResumes();
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

}