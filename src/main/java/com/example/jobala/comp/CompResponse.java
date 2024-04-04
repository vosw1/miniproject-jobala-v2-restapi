package com.example.jobala.comp;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

public class CompResponse {

    //검색
    @AllArgsConstructor
    @Data
    public static class SearchDTO {
        private String career;
        private String edu;
        private String hopeJob;
    }

    //인재 검색
    @Data
    @AllArgsConstructor
    public static class ScoutListDTO{
        private String name;
        private String resumeTitle;
        private Date age;
        private String address;
        private String career;
    }

    //기업 프로필
    @Data
    @AllArgsConstructor
    public static class CompProfileDTO {
        private String name;
        private String password;
        private String phone;
        private String email;
        private String compname;
        private String address;
        private String compNum;
        private String imgFilename;
        private String imgTitle;
    }

    //기업 프로필 업데이트
    @Data
    @AllArgsConstructor
    public static class CProfileUpdateDTO {
        private String name;
        private String password;
        private String phone;
        private String email;
        private String compname;
        private String address;
        private String compNum;
        private Integer id;
        private String imgFilename;
        private String imgTitle;
    }
}
