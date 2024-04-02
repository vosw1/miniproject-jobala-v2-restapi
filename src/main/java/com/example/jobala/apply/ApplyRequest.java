package com.example.jobala.apply;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ApplyRequest {

    // TODO: 이름
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApplyStatusUpdateRequestDTO {
        @NotEmpty
        private Integer applyId;
        @NotEmpty
        private String status;
    }

    // TODO: 이름
    @Data
    @AllArgsConstructor
    public static class ApplyRequestDTO {
        @NotEmpty
        private Integer resumeId;
        @NotEmpty
        private Integer jobopenId;

        public Apply toEntity(Resume resume, Jobopen jobopen, User sessionUser) {
            return Apply.builder()
                    .role(sessionUser.getRole())
                    .user(sessionUser)
                    .jobopen(jobopen)
                    .resume(resume)
                    .state("검토중")
                    .build();
        }
    }
}