package com.example.jobala.guest;

import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala._user.User;
import com.example.jobala._user.UserResponse;
import com.example.jobala.resume.ResumeResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GuestController {

    private final HttpSession session;
    private final GuestService guestService;

    // TODO : /api/mngForm 으로 변경 -> 기업 개인 합쳐서 분기처리 -> UserController
    //이력서 관리 페이징
    @GetMapping("/api/guest/mngForm")
    public ResponseEntity<?> mngForm( @RequestParam(defaultValue = "0") int page) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.MngDTO respDTO = guestService.guestResumesMng(page, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // TODO : /api/profile 으로 변경 -> 기업 개인 합쳐서 분기처리 -> UserController
    // 개인 - 마이페이지 - 프로필
    @GetMapping("/api/guest/profileForm")
    public ResponseEntity<?> profileForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.GuestProfile respDTO = guestService.guestProgile(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // TODO : /api/profile 으로 변경 -> 기업 개인 합쳐서 분기처리 -> UserContoller
    // 개인 - 마이페이지 - 프로필 업데이트
    @PutMapping("/api/guest/profile")
    public ResponseEntity<?> profileUpdate(@RequestBody GuestRequest.GuestProfileUpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.GuestProfile respDTO = guestService.guestUpdateProfile(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }
}