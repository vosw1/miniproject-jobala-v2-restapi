package com.example.jobala.board;

import com.example.jobala._user.User;
import com.example.jobala.reply.Reply;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class BoardResponse {


    // 글 수정
    @Data
    public static class UpdateDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;

        public UpdateDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
        }
    }

    // 글쓰기
    @Data
    public static class SaveDTO {
        private int id;
        private String title;
        private String content;

        public SaveDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
        }
    }

    // TODO: 이름
    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private int userId;
        private String username; // 게시글 작성자 이름
        private boolean isOwner;
        private List<ReplyDTO> replies = new ArrayList<>();

        public DetailDTO(Board board, User sessionUser, List<Reply> repliesList) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername(); // join 해서 가져왔음
            this.isOwner = false;
            if (sessionUser != null) {
                if (sessionUser.getId() == userId) isOwner = true;
            }

            this.replies = repliesList.stream().map(reply -> new ReplyDTO(reply, sessionUser)).toList();
        }

        @Data
        public class ReplyDTO {
            private int id;
            private String comment;
            private int userId; // 댓글 작성자 아이디
            private String username; // 댓글 작성자 이름
            private boolean isOwner;

            public ReplyDTO(Reply reply, User sessionUser) {
                this.id = reply.getId(); // lazy loading 발동
                this.comment = reply.getComment();
                this.userId = reply.getUser().getId();
                this.username = reply.getUser().getUsername(); // lazy loading 발동 (in query)
                this.isOwner = false;
                if (sessionUser != null) {
                    if (sessionUser.getId() == userId) isOwner = true;
                }
            }
        }
    }

    // 글 조회
    @Data
    public static class BoardDTO {
        private Integer id;
        private String title;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private Timestamp createdAt;
        private Integer userId;
        private String username;

        public BoardDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.createdAt = board.getCreatedAt();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();
        }
    }


}