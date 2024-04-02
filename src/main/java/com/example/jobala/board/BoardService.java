package com.example.jobala.board;

import com.example.jobala._core.errors.apiException.ApiException403;
import com.example.jobala._core.errors.exception.Exception403;

import com.example.jobala._user.User;
import com.example.jobala.reply.Reply;
import com.example.jobala.reply.ReplyJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardJPARepository boardJPARepository;
    private final ReplyJPARepository replyJPARepository;

    // 글삭제하기
    public void boardDelete(int boardId, Integer sessionUserId) {
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new ApiException403("게시글을 찾을 수 없습니다."));

        if (sessionUserId != board.getUser().getId()) {
            throw new ApiException403("게시글을 삭제 할 권한이 없습니다.");
        }
        boardJPARepository.deleteById(boardId);
    }

    // 글상세보기
    public BoardResponse.DetailDTO boardDetail(int boardId, User sessionUser) {
        Board board = boardJPARepository.findByIdJoinUser(boardId)
                .orElseThrow(() -> new ApiException403("게시글을 찾을 수 없습니다"));
        List<Reply> replyList = replyJPARepository.findByUserId(boardId);

        return new BoardResponse.DetailDTO(board, sessionUser, replyList);
    }

    // 글수정
    @Transactional
    public BoardResponse.UpdateDTO boardUpdate(int boardId, int sessionUserId, BoardRequest.UpdateDTO reqDTO) {
        // 1. 조회 및 예외처리
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new ApiException403("게시글을 찾을 수 없습니다"));
        // 2. 권한 처리
        if (sessionUserId != board.getUser().getId()) {
            throw new ApiException403("게시글을 수정할 권한이 없습니다");
        }
        // 3. 글수정
        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());

        return new BoardResponse.UpdateDTO(board);
    }

    // 글조회
    public BoardResponse.BoardDTO boardFindById(int boardId) {
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new ApiException403("게시글을 찾을 수 없습니다."));
        return new BoardResponse.BoardDTO(board);
    }

    // 글쓰기
    @Transactional
    public BoardResponse.SaveDTO boardSave(BoardRequest.SaveDTO reqDTO, User sessionUser) {
        Board board = boardJPARepository.save(reqDTO.toEntity(sessionUser));
        return new BoardResponse.SaveDTO(board);
    }


    //DEL: 글조호 삭제
    // TODO: 이름
    public List<BoardResponse.BoardDTO> boardFindAll() { // 글목록조회
        List<BoardResponse.BoardDTO> boardList = boardJPARepository.findBoardAll();
        return boardList;
    }
}