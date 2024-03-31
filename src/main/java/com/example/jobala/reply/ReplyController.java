package com.example.jobala.reply;

import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala._user.User;
import com.example.jobala._user.UserResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ReplyController {
    private final HttpSession session;
    private final ReplyService replyService;

    //댓글 쓰기
    @PostMapping("reply")
    public ResponseEntity<?> save(@RequestBody ReplyRequest.SaveDTO reqDTO){
        UserResponse.LoginResponseDTO sessionUserDTO = (UserResponse.LoginResponseDTO) session.getAttribute("sessionUser");
        replyService.replySave(reqDTO, sessionUserDTO.getUser());
        return ResponseEntity.ok(new ApiUtil(null));
    }

    //댓글 삭제
    @DeleteMapping("reply/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        UserResponse.LoginResponseDTO sessionUserDTO = (UserResponse.LoginResponseDTO) session.getAttribute("sessionUser");
        replyService.replyDelete(id, sessionUserDTO.getUser().getId());
        return ResponseEntity.ok(new ApiUtil(null));
    }
}