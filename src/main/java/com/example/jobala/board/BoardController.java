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
    @GetMapping("/board/{id}")
    public ResponseEntity<?> boardDetailForm(@PathVariable int id) {
        UserResponse.LoginResponseDTO sessionUserDTO = (UserResponse.LoginResponseDTO) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO respDTO = boardService.boardDetail(id, sessionUserDTO.getUser());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 글 목록보기 완료
    @GetMapping("/board")
    public ResponseEntity<?> boardForm(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size, HttpServletRequest req) {
        Page<Board> boardPage = boardService.글목록조회(page, size);

        req.setAttribute("boardList", boardPage.getContent());
        req.setAttribute("first", page == 0 ? true : false);
        req.setAttribute("last", page < boardPage.getTotalPages() - 1);
        req.setAttribute("previousPage", page - 1);
        req.setAttribute("nextPage", page + 1);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStringWithoutTime = sdf.format(date);
        req.setAttribute("currentDate", dateStringWithoutTime);

        List<BoardResponse.MainDetailDTO> respDTO = boardService.boardFindAll();
        System.out.println("respDTO: " + respDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }


    @PutMapping("/board/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody BoardRequest.UpdateDTO reqDTO) {
        UserResponse.LoginResponseDTO sessionUserDTO = (UserResponse.LoginResponseDTO) session.getAttribute("sessionUser");
        boardService.boardUpdate(id, sessionUserDTO.getUser().getId(), reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }


    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, HttpServletRequest request) {
        Board board = boardService.boardFindById(id);
        request.setAttribute("board", board);
        return "board/updateForm";
    }

    //TODO: saveForm삭제 예정
    @GetMapping("/board/saveForm")
    public String saveForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        return "board/saveForm";
    }

    // 글 쓰기 완료
    @PostMapping("/board")
    public ResponseEntity<?> save(@RequestBody BoardRequest.SaveDTO reqDTO) {
        UserResponse.LoginResponseDTO sessionUserDTO = (UserResponse.LoginResponseDTO) session.getAttribute("sessionUser");
        boardService.boardSave(reqDTO, sessionUserDTO.getUser());
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 글 삭제 완료
    @DeleteMapping("/board/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        UserResponse.LoginResponseDTO sessionUserDTO = (UserResponse.LoginResponseDTO) session.getAttribute("sessionUser");

        boardService.boardDelete(id, sessionUserDTO.getUser().getId());
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}