package com.example.jobala.apply;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ApplyRequest {

    // 상태 업데이트
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApplyStatusUpdateRequestDTO {
//        @NotEmpty
        private Integer applyId;
        @NotEmpty
        private String status;
    }

    // 포지션 제안, 지원하기
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