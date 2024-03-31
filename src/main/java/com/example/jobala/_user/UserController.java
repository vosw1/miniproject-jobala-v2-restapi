package com.example.jobala._user;

import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala.jobopen.JobopenJPARepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
        List<UserResponse.MainDTO> respDTO = userService.mainJobopenList(page, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO reqDTO) {
        UserResponse.LoginResponseDTO respDTO = userService.login(reqDTO);
        session.setAttribute("sessionUser", respDTO);
        return ResponseEntity.ok(new ApiUtil(null));
    }

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(UserRequest.JoinDTO reqDTO, HttpServletRequest req) {
        UserResponse.JoinDTO respDTO = userService.join(reqDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    //로그아웃
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();
        return ResponseEntity.ok(new ApiUtil(null));
    }

}
 