package com.example.jobala.resume;

import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala._user.User;
import com.example.jobala._user.UserQueryRepository;
import com.example.jobala.jobopen.JobopenQueryRepository;
import com.example.jobala.scrap.Scrap;
import com.example.jobala.scrap.ScrapQueryRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ResumeController {

    private final HttpSession session;
    private final ResumeService resumeService;

    //이력서 업데이트
    @PostMapping("api/guest/resume/{id}")  // 주소 수정 필요
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ResumeRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.UpdateDTO respDTO = resumeService.resumeUpdate(id, reqDTO,sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // TODO: 글조회로 변경예정
//    @GetMapping("/guest/resume/{id}/updateForm")
//    public String updateForm(@PathVariable Integer id, HttpServletRequest req) {
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        ResumeResponse.DetailDTO respDTO = resumeService.resumeFindById(id, sessionUser);
//        req.setAttribute("resume", respDTO);
//
//        // 업데이트 페이지에서 체크박스 체크 로직
//        ResumeResponse.CheckBoxDTO checkedSkillsList = resumeService.getCheckedSkills(id);
//        req.setAttribute("checkedSkillsList", checkedSkillsList);
//        return "guest/resume/updateForm";
//    }

    //이력서 상세보기
    @GetMapping("/api/guest/resume/{id}")
    public ResponseEntity<?> detailForm(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.DetailDTO respDTO = resumeService.resumeFindById(id, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    //이력서 등록
    @PostMapping("/api/guest/resume")  // 주소 수정 필요
    public ResponseEntity<?> save(@RequestBody ResumeRequest.SaveDTO resumeSaveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.ASaveDTO respDTO = resumeService.resumeSave(resumeSaveDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    //이력서 삭제
    @GetMapping("/api/resume/{id}/delete")  // 주소 수정 필요
    public String delete(@PathVariable int id, ResumeRequest.DeleteDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.resumeDelete(id, reqDTO.getId());
        return "redirect:/guest/mngForm";
    }
}