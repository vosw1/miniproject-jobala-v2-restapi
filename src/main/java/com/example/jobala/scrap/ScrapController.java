package com.example.jobala.scrap;

import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala._user.User;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.ResumeResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScrapController {
    private final HttpSession session;
    private final ScrapService scrapService;

    //기업의 스크랩 목록
    @GetMapping("/comp/scrap")
    public ResponseEntity<?> compScrapForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<ResumeResponse.ScrapDTO> respDTO = scrapService.scrapResumeBycomp(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    //기업 스크랩
    @PostMapping("/comp/scrap")
    public ResponseEntity<?> scrapResume(@RequestBody ScrapRequest.CompScrapDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Scrap scrap = scrapService.scrapByComp(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(scrap));
    }

    // 개인의 스크랩 목록
    @GetMapping("/guest/scrap")
    public ResponseEntity<?> guestScrapForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<JobopenResponse.ScrapDTO> respDTO = scrapService.scrapJobopenByGuest(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    //개인 스크랩
    @PostMapping("/guest/scrap")
    public ResponseEntity<?> scrapJobopen(@RequestBody ScrapRequest.GuestScrap reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Scrap scrap = scrapService.scrapByGuest(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(scrap));
    }

}
