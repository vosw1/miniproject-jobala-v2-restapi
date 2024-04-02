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
    @GetMapping("/api/scraps")
    public ResponseEntity<?> ScrapForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser.getRole() == 1) { // 기업 스크랩 목록
            List<ResumeResponse.ScrapDTO> respDTO = scrapService.scrapResumeBycomp(sessionUser.getId());
            return ResponseEntity.ok(new ApiUtil<>(respDTO));
        } else { // 개인 스크랩 목록
            List<JobopenResponse.ScrapDTO> respDTO = scrapService.scrapJobopenByGuest(sessionUser.getId());
            return ResponseEntity.ok(new ApiUtil<>(respDTO));
        }
    }

    //스크랩
    @RequestMapping(value = "/api/scraps", method = {RequestMethod.POST, RequestMethod.DELETE})
    public ResponseEntity<?> scrapResume(@RequestBody ScrapRequest.ScrapDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser.getRole() == 1) { // 기업이 스크랩
            ScrapResponse.CompDTO respDTO = scrapService.scrapByComp(reqDTO, sessionUser);
            return ResponseEntity.ok(new ApiUtil<>(respDTO));
        } else {
            ScrapResponse.GuestDTO respDTO = scrapService.scrapByGuest(reqDTO, sessionUser);
            return ResponseEntity.ok(new ApiUtil<>(respDTO));
        }
    }
}
