package com.example.jobala.reply;

import com.example.jobala._user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

public class ReplyResponse {

    //댓글 쓰기
    @AllArgsConstructor
    @Data
    public static class ReplyDTO {
        private Integer replyId;
        private Integer userId;
        private String comment;
        private String username;
        private Boolean replyOwner; // 게시글 주인 여부

        public ReplyDTO(Reply reply, User user) {
            this.replyId = reply.getId();
            this.userId = reply.getUser().getId();
            this.comment = reply.getComment();
            this.username = reply.getUser().getUsername();

            if (user == null) replyOwner = false;
            else replyOwner = user.getId().equals(userId);
        }
    }
}