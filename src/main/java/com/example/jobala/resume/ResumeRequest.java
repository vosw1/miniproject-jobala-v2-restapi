package com.example.jobala.resume;

import com.example.jobala._user.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ResumeRequest {

    @AllArgsConstructor
    @Data
    public static class SaveDTO {
        @NotEmpty(message = "이력서 제목은 공백일 수 없습니다")
        @Size(min = 1, max = 100, message = "제목은 1자 이상 100자 이하여야 합니다")
        private String resumeTitle;

        @Pattern(regexp = "^(백엔드|프론트엔드)$", message = "희망직무는 공백일 수 없습니다")
        private String hopeJob;

        @Pattern(regexp = "^(신입|경력)$", message = "경력은 공백일 수 없습니다")
        private String career;

        @NotEmpty(message = "자격증은 공백일 수 없습니다")
        private String license;

        @NotEmpty(message = "마감일은 공백일 수 없습니다")
        private String content;

        @Pattern(regexp = "^(고등학교 졸업|대학교 졸업)$", message = "학력은 공백일 수 없습니다")
        private String edu;

        @Pattern(regexp = "^(JAVA/JavaScript/HTML/jQuery/MySQL/Spring)$", message = "JAVA,JavaScript,HTML,jQuery,MySQL,Spring이외의 스킬은 입력할 수 없습니다.")
        private List<String> skills = new ArrayList<>();

        public Resume toEntity(User user) {
            return Resume.builder()
                    .resumeTitle(resumeTitle)
                    .hopeJob(hopeJob)
                    .career(career)
                    .license(license)
                    .content(content)
                    .edu(edu)
                    .skills(String.valueOf(skills))
                    .user(user)
                    .role(user.getRole())
                    .name(user.getName())
                    .build();
        }

    }

    @AllArgsConstructor
    @Data
    public static class UpdateDTO {
        @NotEmpty(message = "제목은 공백일 수 없습니다")
        @Size(min = 1, max = 100, message = "제목은 1자 이상 100자 이하여야 합니다")
        private String resumeTitle;

        @Pattern(regexp = "^(백엔드|프론트엔드)$", message = "희망직무는 공백일 수 없습니다")
        private String hopeJob;

        @Pattern(regexp = "^(신입|경력)$", message = "경력은 공백일 수 없습니다")
        private String career;

        @NotEmpty(message = "자격증은 공백일 수 없습니다")
        private String license;

        @NotEmpty(message = "마감일은 공백일 수 없습니다")
        private String content;

        @Pattern(regexp = "^(고등학교 졸업|대학교 졸업)$", message = "학력은 공백일 수 없습니다")
        private String edu;

        private List<String> skills = new ArrayList<>();
    }
}