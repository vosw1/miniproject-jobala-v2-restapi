package com.example.jobala._user;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.sql.Date;

public class UserRequest {

    //로그인
    @Data
    public static class LoginDTO {
        @NotEmpty(message = "유저네임이 공백일 수 없습니다")
        @Size(min = 1, max = 20, message = "유저네임은 최소 1자 이상 최대 20자 이하여야 합니다")
        private String username;

        @NotEmpty(message = "비밀번호가 공백일 수 없습니다")
        @Size(min = 4, max = 20, message = "비밀번호는 최소 4자 이상 최대 20자 이하여야 합니다")
        private String password;
    }

    // 프로필 업데이트
    @Data
    public static class UserUpdateDTO {
        @NotEmpty(message = "이름이 공백일 수 없습니다")
        @Size(min = 1, max = 20, message = "이름은 1자 이상 20자 이하여야 합니다")
        private String name;

        @NotEmpty(message = "비밀번호가 공백일 수 없습니다")
        @Size(min = 1, max = 20, message = "비밀번호는 최소 4자 이상 20자 이하 이어야 합니다")
        private String password;

        @NotEmpty(message = "전화번호가 공백일 수 없습니다")
        @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다")
        private String phone;

        @Email(message = "올바른 이메일 형식이어야 합니다")
        @NotEmpty(message = "email이 공백일 수 없습니다")
        private String email;

        @NotEmpty(message = "사진제목은 공백일 수 없습니다")
        private String imgTitle;

        @NotEmpty(message = "주소는 공백일 수 없습니다")
        private String address;

        @NotEmpty(message = "사진경로는 공백일 수 없습니다")
        private String imgFilename;
    }

    //개인 회원가입
    @Data
    public static class GuestJoinDTO {

        @NotEmpty(message = "주소가 공백일 수 없습니다")
        private String address;

        @NotEmpty(message = "유저네임이 공백일 수 없습니다")
        private String username;

        @Email(message = "올바른 이메일 형식이어야 합니다")
        @NotEmpty(message = "email이 공백일 수 없습니다")
        private String email;

        @NotEmpty(message = "비밀번호가 공백일 수 없습니다")
        @Size(min = 4, max = 20, message = "비밀번호는 최소 4자 이상 20장 이하여야 합니다")
        private String password;

        @NotEmpty(message = "이름이 공백일 수 없습니다")
        @Size(min = 1, max = 20, message = "이름은 1자 이상 20자 이하여야 합니다")
        private String name;

        @NotEmpty(message = "전화번호가 공백일 수 없습니다")
        @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다")
        private String phone;

        @NotNull
        @Past(message = "날짜는 과거여야 합니다.")
        private Date age;

        @Min(0)
        @Max(1)
        @NotNull
        private Integer role; // 0 -> guest, 1 -> comp

        public User toEntity() {
            return User.builder()
                    .address(address)
                    .username(username)
                    .email(email)
                    .password(password)
                    .name(name)
                    .phone(phone)
                    .age(age)
                    .role(role)
                    .build();
        }
    }

    //기업 회원가입
    @Data
    public static class CompJoinDTO {

        @Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$", message = "사업자번호 형식이 올바르지 않습니다")
        private String compNum;

        @Size(min = 1, max = 3, message = "ceo명은 최소 1자 이상 최대 20자 이하여야 합니다")
        private String ceo;

        private String compname;

        @NotEmpty(message = "주소가 공백일 수 없습니다")
        private String address;

        @NotEmpty(message = "유저네임이 공백일 수 없습니다")
        private String username;

        @Email(message = "올바른 이메일 형식이어야 합니다")
        @NotEmpty(message = "email이 공백일 수 없습니다")
        private String email;

        @NotEmpty(message = "비밀번호가 공백일 수 없습니다")
        @Size(min = 4, max = 20, message = "비밀번호는 최소 4자 이상 20장 이하여야 합니다")
        private String password;

        @NotEmpty(message = "이름이 공백일 수 없습니다")
        @Size(min = 1, max = 20, message = "이름은 1자 이상 20자 이하여야 합니다")
        private String name;

        @NotEmpty(message = "전화번호가 공백일 수 없습니다")
        @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다")
        private String phone;

        @NotNull
        @Past(message = "날짜는 과거여야 합니다.")
        private Date age;

        @Min(0)
        @Max(1)
        @NotNull
        private Integer role; // 0 -> guest, 1 -> comp

        public User toEntity() {
            return User.builder()
                    .compname(compname)
                    .compNum(compNum)
                    .address(address)
                    .username(username)
                    .email(email)
                    .name(name)
                    .password(password)
                    .phone(phone)
                    .age(age)
                    .role(role)
                    .build();
        }
    }
}
