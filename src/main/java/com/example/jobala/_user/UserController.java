package com.example.jobala._user;

import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala.guest.GuestResponse;
import com.example.jobala.jobopen.JobopenJPARepository;
import com.example.jobala.jobopen.JobopenResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JobopenJPARepository jobopenJPARepository;
    private final HttpSession session;

    // TODO : 중복체크 로직 삭제

    //메인에서 공고목록보기
    @GetMapping("/")
    public ResponseEntity<?> mainForm(@RequestParam(defaultValue = "0") Integer page, HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.MainDTO respDTO = userService.mainJobopenList(page, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO reqDTO, HttpSession session) {
        User sessionUser = userService.login(reqDTO);
        session.setAttribute("sessionUser", sessionUser);
        return ResponseEntity.ok(new ApiUtil(null));
    }

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO reqDTO) {
        UserResponse.JoinDTO respDTO = userService.join(reqDTO);
        // TODO : ?? - 찬혁
        return ResponseEntity.ok(new ApiUtil(respDTO.getCompDTO()));
    }

    //로그아웃
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();
        return ResponseEntity.ok(new ApiUtil(null));
    }


    //기업,개인 - 채용공고 검색필터
    @GetMapping("/jobopenSearch")
    public ResponseEntity<?> jobopenSearch(HttpServletRequest req, @RequestParam(value = "skills", defaultValue = "") String skills, @RequestParam GuestResponse.SearchDTO resDTO) {
        List<JobopenResponse.ListDTO> respDTO = userService.jobopenSearch(skills, resDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // 기업,개인 - 채용공고 목록
    @GetMapping("/jobSearch")
    public ResponseEntity<?> jobSearch(HttpServletRequest req) {
        List<JobopenResponse.ListDTO> respDTO = userService.findAll();
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }
}
 