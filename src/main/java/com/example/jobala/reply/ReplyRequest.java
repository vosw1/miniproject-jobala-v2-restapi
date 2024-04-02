package com.example.jobala.reply;

import com.example.jobala._user.User;
import com.example.jobala.board.Board;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class ReplyRequest {
    @Data
    public static class SaveDTO {
        @NotNull(message = "댓글은 공백일 수 없습니다")
        private String comment;

        public Reply toEntity(User sessionUser, Board board) {
            return Reply.builder()
                    .comment(comment)
                    .board(board)
                    .user(sessionUser)
                    .build();
        }
    }
}