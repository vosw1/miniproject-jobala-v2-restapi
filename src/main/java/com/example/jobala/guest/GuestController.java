package com.example.jobala.guest;

import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala._user.User;
import com.example.jobala._user.UserJPARepository;
import com.example.jobala._user.UserResponse;
import com.example.jobala._user.UserService;
import com.example.jobala.jobopen.JobopenJPARepository;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.Resume;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GuestController {

    private final HttpSession session;
    private final GuestQueryRepository guestRepository;
    private final GuestService guestService;
    private final UserJPARepository userJPARepository;
    private final GuestQueryRepository guestQueryRepository;
    private final GuestJPARepository guestJPARepository;
    private final JobopenJPARepository jobopenJPARepository;

    private final UserService userService;

    // DEL: mainForm 삭제

    //기업,개인 - 채용공고 검색필터
    @GetMapping("/guest/jobopenSearch")
    public ResponseEntity<?> jobopenSearch(HttpServletRequest req, @RequestParam(value = "skills", defaultValue = "") String skills, GuestResponse.SearchDTO resDTO) {
        List<JobopenResponse.ListDTO> respDTO = guestService.jobopenSearch(skills, resDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }


    // 기업,개인 - 채용공고 목록
    @GetMapping("/guest/jobSearch")
    public ResponseEntity<?> jobSearch(HttpServletRequest req) {
        List<JobopenResponse.ListDTO> respDTO = guestService.findAll();
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    //이력서 관리 페이징
    @GetMapping("/guest/mngForm")
    public ResponseEntity<?> mngForm(HttpServletRequest req, @RequestParam(defaultValue = "0") int page) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Page<Resume> respDTO = guestService.resumesFindAll(page);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }


    @GetMapping("/guest/profileForm")
    public ResponseEntity<?> profileForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.CompProfile respDTO = guestService.guestProgile(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @PutMapping("/guest/Profile") // 주소 수정 필요!
    public ResponseEntity<?> updateProfile(GuestRequest.GuestProfileUpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.CompProfile respDTO = guestService.guestUpdateProfile(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));

    }
}