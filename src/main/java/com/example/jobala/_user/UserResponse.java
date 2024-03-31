package com.example.jobala._user;


import com.example.jobala.jobopen.Jobopen;
import lombok.Data;

public class UserResponse {

    @Data
    public static class JoinDTO {
        private GuestDTO guestDTO; // 개인 회원가입
        private CompDTO compDTO; // 기업 회원가입

        private class GuestDTO {
            private Integer id;
            private String username; // 아이디
            private String name; // 이름
            private String email; //이메일
            private String address; //주소
            private Integer role; // 0 -> guest, 1 -> comp
        }

        private class CompDTO {
            private Integer id;
            private String username; // 아이디
            private String name; // 담당자 이름
            private String compname; // 회사명
            private String email; //이메일
            private String ceo; // 기업 대표명
            private Integer role; // 0 -> guest, 1 -> comp
        }
    }


    @Data
    public static class MainDTO {
        private Integer userId;
        private String imgFilename; // 파일 패스
        private Integer jobopenId; //공고제목
        private String jobopenTitle; //공고제목
        private String compLocation; //공고제목
        private String career; //공고제목
        private String edu; //공고제목
        private String endTime; //공고제목

        public MainDTO(Jobopen jobopen, User user) {
            this.userId = user.getId();
            this.imgFilename = user.getImgFilename();
            this.jobopenId = jobopen.getId();
            this.jobopenTitle = jobopen.getJobopenTitle();
            this.compLocation = jobopen.getCompLocation();
            this.career = jobopen.getCareer();
            this.edu = jobopen.getEdu();
            this.endTime = String.valueOf(jobopen.getEndTime());
        }
    }


}
