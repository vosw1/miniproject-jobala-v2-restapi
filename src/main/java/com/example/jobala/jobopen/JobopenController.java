package com.example.jobala.jobopen;

import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala._user.SessionUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class JobopenController {

    private final HttpSession session;
    private final JobopenService jobopenService;

    //공고 삭제
    @DeleteMapping("/api/jobopens/{id}")  // 주소 수정 필요
    public ResponseEntity<?> delete(@PathVariable int id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        jobopenService.jobopenDelete(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil(null));
    }

    //공고 수정
    @PutMapping("/api/jobopens/{id}")  // 주소 수정 필요
    public ResponseEntity<?> update(@Valid @PathVariable Integer id, @RequestBody JobopenRequest.UpdateDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        JobopenResponse.UpdateDTO respDTO = jobopenService.jobopenUpdate(id, sessionUser, reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }


    //공고 등록
    @PostMapping("/api/jobopens")  // 주소 수정 필요
    public ResponseEntity<?> jobopenSave(@Valid @RequestBody JobopenRequest.SaveDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        JobopenResponse.SaveDTO respDTO = jobopenService.jobopenSave(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    //공고 보기
    @GetMapping("/api/jobopens/{id}")
    public ResponseEntity<?> detailForm(@PathVariable int id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        // 채용공고 정보 가져오기
        JobopenResponse.DetailDTO respDTO = jobopenService.findJobopenById(id, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

}