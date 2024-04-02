package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.sql.Update;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class JobopenRequest {

    @AllArgsConstructor
    @Data
    public static class DeleteDTO {
        private int id;
    }

    @AllArgsConstructor
    @Data
    public static class JobopenDetailDTO {
        @Pattern(regexp = "^(고등학교 졸업|대학교 졸업)$", message = "학력은 공백일 수 없습니다")
        private String edu;

        @NotEmpty(message = "채용공고 제목은 공백일 수 없습니다")
        @Size(min = 1, max = 100, message = "제목은 1자 이상 100자 이하여야 합니다")
        private String jobopenTitle;

        @Pattern(regexp = "^(신입|경력)$", message = "경력은 공백일 수 없습니다")
        private String career;

        @Pattern(regexp = "^(계약직|정규직)$", message = "고용형태는 공백일 수 없습니다")
        private String jobType;

        @Pattern(regexp = "^(협의|3000만원 이상|5000만원 이상)$", message = "연봉은 공백일 수 없습니다")
        private String salary;

        @Pattern(regexp = "^(백엔드|프론트엔드)$", message = "희망직무는 공백일 수 없습니다")
        private String hopeJob;

        @NotEmpty(message = "근무지역은 공백일 수 없습니다")
        private String compLocation;

        @NotEmpty(message = "내용은 공백일 수 없습니다")
        @Size(min = 1, max =1000, message = "내용은 1자 이상 1000자 이하여야 합니다")
        private String content;

        @NotEmpty(message = "마감일은 공백일 수 없습니다")
        private Date endTime;
        private List<String> skills = new ArrayList<>(); //내용
    }

    @AllArgsConstructor
    @Data
    public static class UpdateDTO {
        //compname = ? ,jobopenTitle=? , career=?, edu=?, jobType=?,salary=?,compLocation=?,content=? ,skills =?

        @NotEmpty(message = "채용공고 제목은 공백일 수 없습니다")
        @Size(min = 1, max = 100, message = "제목은 1자 이상 100자 이하여야 합니다")
        private String jobopenTitle; //공고제목

        @Pattern(regexp = "^(신입|경력)$", message = "경력은 공백일 수 없습니다")
        private String career;// 경력

        @Pattern(regexp = "^(고등학교 졸업|대학교 졸업)$", message = "학력은 공백일 수 없습니다")
        private String edu; // 학력

        @Pattern(regexp = "^(계약직|정규직)$", message = "고용형태는 공백일 수 없습니다")
        private String jobType; // 고용형태

        @Pattern(regexp = "^(협의|3000만원 이상|5000만원 이상)$", message = "연봉은 공백일 수 없습니다")
        private String salary; //연봉

        @Pattern(regexp = "^(백엔드|프론트엔드)$", message = "희망직무는 공백일 수 없습니다")
        private String hopeJob;//희망직무

        @NotEmpty(message = "근무지역은 공백일 수 없습니다")
        private String compLocation; //근무지역

        @NotEmpty(message = "마감일은 공백일 수 없습니다")
        private Date endTime; //내용

        private List<String> skills = new ArrayList<>();//스킬

    }

    @AllArgsConstructor
    @Data
    public static class SaveDTO {
        @Pattern(regexp = "^(고등학교 졸업|대학교 졸업)$", message = "학력은 공백일 수 없습니다")
        private String edu;

        @NotEmpty(message = "채용공고 제목은 공백일 수 없습니다")
        @Size(min = 1, max = 100, message = "제목은 1자 이상 100자 이하여야 합니다")
        private String jobopenTitle; //공고제목

        @Pattern(regexp = "^(신입|경력)$", message = "경력은 공백일 수 없습니다")
        private String career;// 경력

        @Pattern(regexp = "^(계약직|정규직)$", message = "고용형태는 공백일 수 없습니다")
        private String jobType; // 고용형태

        @Pattern(regexp = "^(협의|3000만원 이상|5000만원 이상)$", message = "연봉은 공백일 수 없습니다")
        private String salary; //연봉

        @Pattern(regexp = "^(백엔드|프론트엔드)$", message = "희망직무는 공백일 수 없습니다")
        private String hopeJob;//희망직무

        @NotEmpty(message = "근무지역은 공백일 수 없습니다")
        private String compLocation; //근무지역

        @NotEmpty(message = "마감일은 공백일 수 없습니다")
        private Date endTime; //내용

        private List<String> skills = new ArrayList<>(); //내용

        public Jobopen toEntity(User user) {
            return Jobopen.builder()
                    .skills(String.valueOf(skills))
                    .user(user)
                    .role(user.getRole())
                    .edu(edu)
                    .jobopenTitle(jobopenTitle)
                    .career(career)
                    .jobType(jobType)
                    .salary(salary)
                    .hopeJob(hopeJob)
                    .compLocation(compLocation)
                    .endTime(endTime)
                    .build();
        }
    }
}