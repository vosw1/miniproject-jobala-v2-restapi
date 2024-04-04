package com.example.jobala.comp;


import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala.apply.ApplyJPARepository;
import com.example.jobala.resume.ResumeResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompController {
    private final CompService compService;

    //기업 -인재 이력서 검색하기
    @GetMapping("/api/resumeSearch")
    public ResponseEntity<?> resumeSearch(@RequestParam(value = "skills", defaultValue = "") String skills, CompRequest.SearchDTO resDTO) {
        List<ResumeResponse.ScoutListDTO> respDTO = compService.searchResumes(skills, resDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }


}