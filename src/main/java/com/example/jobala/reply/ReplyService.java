package com.example.jobala.reply;

import com.example.jobala._core.errors.apiException.ApiException403;
import com.example.jobala._core.errors.apiException.ApiException404;
import com.example.jobala._user.SessionUser;
import com.example.jobala._user.User;
import com.example.jobala._user.UserJPARepository;
import com.example.jobala.board.Board;
import com.example.jobala.board.BoardJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyJPARepository replyJPARepository;
    private final BoardJPARepository boardJPARepository;
    private final UserJPARepository userJPARepository;

    // 댓글쓰기
    @Transactional
    public ReplyResponse.ReplyDTO replySave(ReplyRequest.SaveDTO reqDTO, SessionUser sessionUser) {
        User user = userJPARepository.findById(sessionUser.getId()).orElseThrow(() -> new ApiException404("해당하는 회원정보를 찾을 수 없습니다."));

        Board board = boardJPARepository.findById(reqDTO.getBoardId())
                .orElseThrow(() -> new ApiException404("없는 게시글에 댓글을 작성 할 수 없습니다."));

        Reply reply = reqDTO.toEntity(user, board);
        replyJPARepository.save(reply);
        return new ReplyResponse.ReplyDTO(reply, user);
    }

    // 댓글삭제
    @Transactional
    public void replyDelete(int replyId, SessionUser sessionUser) {
        Reply reply = replyJPARepository.findById(replyId)
                .orElseThrow(() -> new ApiException404("없는 댓글을 삭제할 수 없어요"));

        if (reply.getUser().getId() != sessionUser.getId()) {
            throw new ApiException403("댓글을 삭제할 권한이 없어요");
        }

        replyJPARepository.deleteById(replyId);
    }
}
