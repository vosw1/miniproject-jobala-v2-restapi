package com.example.jobala.guest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

public class GuestRequest {

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