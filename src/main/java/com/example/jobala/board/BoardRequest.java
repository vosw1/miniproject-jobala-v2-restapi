package com.example.jobala.board;

import com.example.jobala._user.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        @NotEmpty(message = "제목은 공백일 수 없습니다")
        private String title;
        @NotEmpty(message = "내용은 공백일 수 없습니다")
        private String content;

        public Board toEntity(User sessionUser) {
            Board board = Board.builder()
                    .title(title)
                    .content(content)
                    //유저 객체 받아오기
                    .user(sessionUser)
                    .build();
            return board;
        }
    }

    @Data
    public static class UpdateDTO {
        @NotEmpty(message = "제목은 공백일 수 없습니다")
        private String title;
        @NotEmpty(message = "내용은 공백일 수 없습니다")
        private String content;
    }
}