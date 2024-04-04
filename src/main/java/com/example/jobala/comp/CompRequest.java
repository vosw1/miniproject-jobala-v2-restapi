package com.example.jobala.comp;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

public class CompRequest {

    //검색하기
    @AllArgsConstructor
    @Data
    public static class SearchDTO {
        @Pattern(regexp = "^(신입|경력)$", message = "경력은 비어있을 수 없습니다")
        private String career;

        @Pattern(regexp = "^(고등학교 졸업|대학교 졸업)$", message = "학력은 비어있을 수 없습니다")
        private String edu;

        @Pattern(regexp = "^(백엔드|프론트엔드)$", message = "희망직종은 비어있을 수 없습니다")
        private String hopeJob;
    }
}
