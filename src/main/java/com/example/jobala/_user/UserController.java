package com.example.jobala._user;

import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala.comp.CompRequest;
import com.example.jobala.comp.CompService;
import com.example.jobala.guest.GuestRequest;
import com.example.jobala.guest.GuestService;
import com.example.jobala.jobopen.JobopenJPARepository;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.ResumeResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final HttpSession session;
    private final UserService userService;
    private final GuestService guestService;
    private final CompService compService;

    //메인에서 공고목록보기
    @GetMapping("/")
    public ResponseEntity<?> mainForm(@RequestParam(defaultValue = "0") Integer page) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.MainDTO respDTO = userService.mainJobopenList(page, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserRequest.LoginDTO reqDTO) {
        String jwt = userService.login(reqDTO);
        return ResponseEntity.ok().header("Authorization", jwt).body(new ApiUtil(null));
    }

    // 개인 회원가입 - params = "role=0" -> 개인
    @PostMapping(value = "/join", params = "role=0")
    public ResponseEntity<?> joinGuest(@Valid @RequestBody UserRequest.GuestJoinDTO reqDTO) {
        UserResponse.GuestDTO respDTO = userService.joinGuest(reqDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // 기업 회원가입 - params = "role=1" -> 기업
    @PostMapping(value = "/join", params = "role=1")
    public ResponseEntity<?> joinComp(@Valid @RequestBody UserRequest.CompJoinDTO reqDTO) {
        UserResponse.CompDTO respDTO = userService.joinComp(reqDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    //로그아웃
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();
        return ResponseEntity.ok(new ApiUtil(null));
    }


    //기업,개인 - 채용공고 검색필터
    @GetMapping("/api/jobopenSearch")
    public ResponseEntity<?> jobopenSearch(@RequestParam(value = "skills", defaultValue = "") String skills, GuestRequest.SearchDTO resDTO) {
        List<JobopenResponse.ListDTO> respDTO = userService.jobopenSearch(skills, resDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // 마이페이지 - 이력서, 공고 관리
    @GetMapping("/api/mngForm")
    public ResponseEntity<?> mngForm(@RequestParam(defaultValue = "0") int page) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        if (sessionUser.getRole() == 0) { // 개인 마이페이지
            ResumeResponse.MngDTO respDTO = guestService.guestResumesMng(page, sessionUser);
            return ResponseEntity.ok(new ApiUtil(respDTO));
        } else { // 기업 마이페이지
            List<JobopenResponse.MngDTO> respDTO = compService.compJobopenMng(sessionUser);
            return ResponseEntity.ok(new ApiUtil(respDTO));
        }
    }

    // 마이페이지 - 프로필
    @GetMapping("/api/profile")
    public ResponseEntity<?> profileForm() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        if (sessionUser.getRole() == 0) { // 개인 프로필
            UserResponse.GuestProfile respDTO = guestService.guestProgile(sessionUser);
            return ResponseEntity.ok(new ApiUtil(respDTO));
        } else { // 기업 프로필
            UserResponse.GuestProfile respSTO = compService.compProfile(sessionUser);
            return ResponseEntity.ok(new ApiUtil(respSTO));
        }
    }

    // 마이페이지 프로필 업데이트
    @PutMapping("/api/profile")
    public ResponseEntity<?> profileUpdate(@Valid @RequestBody UserRequest.UserUpdateDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        if (sessionUser.getRole() == 0) { // 개인 프로필 업데이트
            UserResponse.GuestProfile respDTO = guestService.guestUpdateProfile(reqDTO, sessionUser);
            return ResponseEntity.ok(new ApiUtil(respDTO));
        } else { // 기업 프로필 업데이트
            UserResponse.CompProfile respSTO = compService.compUpdateProfile(reqDTO, sessionUser);
            return ResponseEntity.ok(new ApiUtil(respSTO));
        }
    }

}
 