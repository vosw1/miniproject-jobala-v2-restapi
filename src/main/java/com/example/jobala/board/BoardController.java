package com.example.jobala.board;

import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala._user.User;
import com.example.jobala._user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;

    // 글 상세보기 완료
    @GetMapping("/api/boards/{id}")
    public ResponseEntity<?> boardDetailForm(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO respDTO = boardService.boardDetail(id, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 글 목록보기 완료
    @GetMapping("api/boards")
    public ResponseEntity<?> board() {
        List<BoardResponse.BoardDTO> respDTO = boardService.boardFindAll();
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PutMapping("/api/boards/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.UpdateDTO respDTO = boardService.boardUpdate(id, sessionUser.getId(), reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }




    // 글 쓰기 완료
    @PostMapping("/api/boards")
    public ResponseEntity<?> save(@RequestBody BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.SaveDTO respDTO = boardService.boardSave(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // 글 삭제 완료
    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        boardService.boardDelete(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}