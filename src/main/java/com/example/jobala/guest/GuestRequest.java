package com.example.jobala.guest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class GuestRequest {


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class SearchDTO {
        private String career;
        private String compLocation;
        private String edu;
        private String salary;
        private String hopeJob;
        private String jobType;
    }

    //개인 프로필 업데이트
    @Data
    public static class GuestProfileUpdateDTO {
        private String name;
        private String password;
        private String phone;
        private String email;
        private String imgTitle;
    }
}