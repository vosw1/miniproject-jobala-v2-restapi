package com.example.jobala.comp;


import com.example.jobala._user.User;
import com.example.jobala._user.UserJPARepository;
import com.example.jobala.apply.ApplyJPARepository;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.ResumeJPARepository;
import com.example.jobala.resume.ResumeResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompController {

    private final HttpSession session;
    private final CompQueryRepository compQueryRepository;
    private final ApplyJPARepository applyJPARepository;
    private final CompService compService;
    private final UserJPARepository userJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    private final CompJPARepository compJPARepository;


    @GetMapping("/comp/resumeSearch")
    public String jobopenSearch(HttpServletRequest req, @RequestParam(value = "skills", defaultValue = "") String skills, CompResponse.SearchDTO resDTO) {
        List<ResumeResponse.ListDTO> resumeList = compService.searchResumes(skills, resDTO);
        req.setAttribute("resumeList", resumeList);
        return "comp/scoutList";
    }

    @GetMapping("/comp/scoutList")
    public String scoutList(HttpServletRequest req) {
        List<ResumeResponse.ListDTO> resumeList = compService.listAllResumes();
        req.setAttribute("resumeList", resumeList);
        return "comp/scoutList";
    }

    // DEL: getResumeList 삭제

    @GetMapping("/comp/mngForm")
    public String mngForm(HttpServletRequest req) {
        User sessionUser = (User) req.getSession().getAttribute("sessionUser");
        List<JobopenResponse.DTO> jobopenList = compService.searchjobopenList(sessionUser.getId());
        req.setAttribute("jobopenList", jobopenList);
        return "comp/_myPage/mngForm";
    }

    @GetMapping("/comp/profileForm")
    public String profileForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User compProfile = compService.getCompanyProfile(sessionUser.getId());
        req.setAttribute("compProfile", compProfile);
        return "comp/_myPage/profileForm";
    }


    @PostMapping("/comp/updateProfile") // 주소 수정 필요!
    public String updateProfile(CompRequest.CompProfileUpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        compService.compUpdateProfile(reqDTO, sessionUser);
        return "redirect:/comp/profileForm";
    }
}