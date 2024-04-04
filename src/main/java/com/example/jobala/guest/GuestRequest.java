package com.example.jobala.guest;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class GuestRequest {

    //인재검색
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class SearchDTO {
        @Pattern(regexp = "^(신입|경력)$", message = "경력은 공백일 수 없습니다")
        private String career;

        @NotEmpty(message = "근무지역이 공백일 수 없습니다")
        private String compLocation;

        @Pattern(regexp = "^(고등학교 졸업|대학교 졸업)$", message = "학력은 공백일 수 없습니다")
        private String edu;

        @Pattern(regexp = "^(협의|3000만원 이상|5000만원 이상)$", message = "연봉은 공백일 수 없습니다")
        private String salary;

        @Pattern(regexp = "^(백엔드|프론트엔드)$", message = "희망직종은 공백일 수 없습니다")
        private String hopeJob;

        @Pattern(regexp = "^(계약직|정규직)$", message = "고용형태는 공백일 수 없습니다")
        private String jobType;
    }
}