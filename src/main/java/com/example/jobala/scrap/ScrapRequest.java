package com.example.jobala.scrap;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;

public class ScrapRequest {

    //공고를 스크랩
    @AllArgsConstructor
    @Data
    public static class ScrapDTO {

        private Integer jobopenId;

        private Integer resumeId;

        public Scrap toEntity(Jobopen jobopen, User user) {
            return Scrap.builder()
                    .user(user)
                    .jobopen(jobopen)
                    .resume(null)
                    .role(user.getRole())
                    .build();
        }

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
