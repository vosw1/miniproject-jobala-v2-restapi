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

    // DEL: mainForm 삭제

    //이력서 관리 페이징
    @GetMapping("/api/guest/mngForm")
    public ResponseEntity<?> mngForm(HttpServletRequest req, @RequestParam(defaultValue = "0") int page) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.MngDTO respDTO = guestService.guestResumesMng(page, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @GetMapping("/api/guest/profileForm")
    public ResponseEntity<?> profileForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.GuestProfile respDTO = guestService.guestProgile(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @PutMapping("/api/guest/profile")
    public ResponseEntity<?> updateProfile(@RequestBody GuestRequest.GuestProfileUpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.GuestProfile respDTO = guestService.guestUpdateProfile(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));

    }
}