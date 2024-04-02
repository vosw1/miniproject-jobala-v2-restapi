package com.example.jobala.apply;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ApplyRequest {

    // 상태 업데이트
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApplyStatusUpdateRequestDTO {
        @NotNull(message = "applyId는 공백일 수 없습니다")
        private Integer applyId;

        private String status;
    }

    // 포지션 제안, 지원하기
    @Data
    @AllArgsConstructor
    public static class ApplyRequestDTO {
        @NotNull(message = "applyId는 공백일 수 없습니다")
        private Integer applyId;

        @Pattern(regexp = "^(열람전|합격|불합격|수락|거절)$", message = "status는 공백일 수 없습니다")
        private String status;

        public Apply toEntity(Resume resume, Jobopen jobopen, User user ) {
            return Apply.builder()
                    .role(user.getRole())
                    .user(user)
                    .jobopen(jobopen)
                    .resume(resume)
                    .state("검토중")
                    .build();
        }
    }
}