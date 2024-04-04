package com.example.jobala.scrap;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;

public class ScrapRequest {

    // 스크랩하기
    @AllArgsConstructor
    @Data
    public static class DTO {

        private Integer jobopenId;

        private Integer resumeId;

        // 개인이 채용공고 스크랩
        public Scrap toEntity(Jobopen jobopen, User user) {
            return Scrap.builder()
                    .user(user)
                    .jobopen(jobopen)
                    .resume(null)
                    .role(user.getRole())
                    .build();
        }

        // 기업이 인재 스크랩
        public Scrap toEntity(Resume resume, User user) {
            return Scrap.builder()
                    .user(user)
                    .jobopen(null)
                    .resume(resume)
                    .role(user.getRole())
                    .build();
        }
    }
}
