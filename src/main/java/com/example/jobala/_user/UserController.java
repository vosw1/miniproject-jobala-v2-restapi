package com.example.jobala._user;

import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala.jobopen.JobopenJPARepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JobopenJPARepository jobopenJPARepository;
    private final HttpSession session;

    //메인에서 공고목록보기
    @GetMapping("/")
    public ResponseEntity<?> mainForm(@RequestParam(defaultValue = "0") Integer page, HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<UserResponse.MainDTO> respDTO = userService.mainJobopenList(page, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    //서비스 변경 완료
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO, HttpSession session) {
        User sessionUser = userService.login(reqDTO);
        session.setAttribute("sessionUser", sessionUser);
        Boolean isCheck = sessionUser.getRole() == 0;
        session.setAttribute("isCheck", isCheck);
        return "redirect:/";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO, HttpServletRequest req) {
        User user = userService.join(reqDTO);
        req.setAttribute("user", user);
        return "/user/loginForm";
    }

    // TODO : 중복체크 로직 삭제

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

}
 