package com.example.jobala.guest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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

    @Data
    public static class GuestProfileUpdateDTO {
        private String name;
        private String password;
        private String phone;
        private String email;
        private String imgTitle;
        private String imgFilename;
    }
}