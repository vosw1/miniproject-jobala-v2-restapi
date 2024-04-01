package com.example.jobala.jobopen;

import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala._user.User;
import com.example.jobala._user.UserResponse;
import com.example.jobala.guest.GuestQueryRepository;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeQueryRepository;
import com.example.jobala.scrap.Scrap;
import com.example.jobala.scrap.ScrapQueryRepository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class JobopenController {

    private final HttpSession session;
    private final JobopenService jobopenService;

    //공고 삭제
    @DeleteMapping("/comp/jobopen/{id}/delete")  // 주소 수정 필요
    public ResponseEntity<?> delete(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        jobopenService.jobopenDelete(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil(null));
    }

    //공고 수정
    @PutMapping("/api/comp/jobopen/{id}")  // 주소 수정 필요
    public ResponseEntity<?> update(@PathVariable Integer id, JobopenRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        JobopenResponse.UpdateDTO respDTO = jobopenService.jobopenUpdate(id, sessionUser, reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }



    //공고 등록
    @PostMapping("/comp/jobopen/save")  // 주소 수정 필요
    public ResponseEntity<?> jobopenSave(@RequestBody JobopenRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        JobopenResponse.SaDTO respDTO = jobopenService.jobopenSave(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    //공고 보기
    @GetMapping("/comp/jobopen/{id}")
    public ResponseEntity<?> detailForm(@PathVariable int id) {
       User sessionUser = (User) session.getAttribute("sessionUser");

        // 채용공고 정보 가져오기
        JobopenResponse.DetailDTO respDTO = jobopenService.findJobopenById(id, sessionUser);


        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

}