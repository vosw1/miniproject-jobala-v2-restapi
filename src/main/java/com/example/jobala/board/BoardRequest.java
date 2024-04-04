package com.example.jobala.board;

import com.example.jobala._user.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class BoardRequest {

    // 글 저장
    @Data
    public static class SaveDTO {
        @NotEmpty(message = "제목은 공백일 수 없습니다")
        @Size(min = 1, max = 100, message = "제목은 1자 이상 100자 이하여야 합니다")
        private String title;

        @NotEmpty(message = "내용은 공백일 수 없습니다")
        @Size(min = 1, max = 255, message = "내용은 1자 이상 225자 이하여야 합니다")
        private String content;

        public Board toEntity(User user) {
            Board board = Board.builder()
                    .title(title)
                    .content(content)
                    .user(user)
                    .build();
            return board;
        }
    }

    // 글 업데이트
    @Data
    public static class UpdateDTO {
        @NotEmpty(message = "제목은 공백일 수 없습니다")
        @Size(min = 1, max = 100, message = "제목은 1자 이상 100자 이하여야 합니다")
        private String title;

        @NotEmpty(message = "내용은 공백일 수 없습니다")
        @Size(min = 1, max = 255, message = "내용은 1자 이상 225자 이하여야 합니다")
        private String content;
    }
}