package com.example.jobala.reply;

import com.example.jobala._user.User;
import com.example.jobala.board.Board;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class ReplyRequest {

    // 댓글 쓰기
    @Data
    public static class SaveDTO {
        @NotNull(message = "댓글은 공백일 수 없습니다")
        @Size(min = 1, max = 100, message = "댓글은 100자 이하여야 합니다")
        private String comment;

        @NotNull(message = "boardId는 공백일 수 없습니다")
        private Integer boardId;

        public Reply toEntity(User user, Board board) {
            return Reply.builder()
                    .comment(comment)
                    .board(board)
                    .user(user)
                    .build();
        }
    }
}