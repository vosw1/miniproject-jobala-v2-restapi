package com.example.jobala.comp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

public class CompRequest {

    //TODO: 이름
    @AllArgsConstructor
    @Data
    public static class SearchDTO {
        private String career;
        private String edu;
        private String hopeJob;
    }


    // DEL: Response에서 ScoutListDTO 만듬


    //TODO: 이름
    @Getter
    @AllArgsConstructor
    public static class CompProfileUpdateDTO {
        private String name;
        private String password;
        private String phone;
        private String email;
        private String address;
        private String imgTitle;
        private String imgFilename;
    }
}
