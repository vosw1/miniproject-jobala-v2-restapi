package com.example.jobala.scrap;

import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala._user.User;
import com.example.jobala._user.UserResponse;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.ResumeResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScrapController {
    private final HttpSession session;
    private final ScrapService scrapService;

    //스크랩 목록
    @GetMapping("api/scrap")
    public ResponseEntity<?> ScrapForm() {
        UserResponse.LoginResponseDTO sessionUser = (UserResponse.LoginResponseDTO) session.getAttribute("sessionUser");
        if (sessionUser.getUser().getRole() == 1) { // 기업 스크랩 목록
            List<ResumeResponse.ScrapDTO> respDTO = scrapService.scrapResumeBycomp(sessionUser.getUser().getId());
            return ResponseEntity.ok(new ApiUtil<>(respDTO));
        } else { // 개인 스크랩 목록
            List<JobopenResponse.ScrapDTO> respDTO = scrapService.scrapJobopenByGuest(sessionUser.getUser().getId());
            return ResponseEntity.ok(new ApiUtil<>(respDTO));
        }
    }

    //스크랩
    @RequestMapping(value = "/api/scrap", method = {RequestMethod.POST, RequestMethod.DELETE})
    public ResponseEntity<?> scrapResume(@RequestBody ScrapRequest.ScrapDTO reqDTO) {
        UserResponse.LoginResponseDTO sessionUser = (UserResponse.LoginResponseDTO) session.getAttribute("sessionUser");
        if (sessionUser.getUser().getRole() == 1) { // 기업이 스크랩
            Scrap scrap = scrapService.scrapByComp(reqDTO, sessionUser.getUser());
            return ResponseEntity.ok(new ApiUtil<>(scrap));
        } else {
            Scrap scrap = scrapService.scrapByGuest(reqDTO, sessionUser.getUser());
            return ResponseEntity.ok(new ApiUtil<>(scrap));
        }
    }
}
