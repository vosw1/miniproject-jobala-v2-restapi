package com.example.jobala.reply;

import com.example.jobala._user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

public class ReplyResponse {

    @AllArgsConstructor
    @Data
    public static class ReplyDTO {
        private Integer id;
        private Integer userId;
        private String comment;
        private String username;
        private Boolean replyOwner; // 게시글 주인 여부

        public ReplyDTO(Reply reply, User sessionUser) {
            this.id = reply.getId();
            this.userId = userId;
            this.comment = comment;
            this.username = username;

            if (sessionUser == null) replyOwner = false;
            else replyOwner = sessionUser.getId().equals(userId);
        }
    }
}