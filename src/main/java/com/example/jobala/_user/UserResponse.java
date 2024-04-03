package com.example.jobala._user;


import com.example.jobala.jobopen.Jobopen;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class UserResponse {

    //기업 - 프로필 & 업데이트
    @Data
    public static class CompProfile {
        private Integer userId;
        private String name;
        private String compname;
        private String email;
        private String imgFilename;

        public CompProfile(User user) {
            this.userId = user.getId();
            this.name = user.getName();
            this.compname = user.getCompname();
            this.email = user.getEmail();
            this.imgFilename = user.getImgFilename();
        }
    }

    //개인 - 프로필 & 업데이트
    @Data
    public static class GuestProfile {
        private Integer userId;
        private String name;
        private String email;
        private String imgFilename;

        public GuestProfile(User user) {
            this.userId = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.imgFilename = user.getImgFilename();
        }
    }

    // 개인, 기업 - 회원가입

    @Data
    public static class GuestDTO {
        private Integer userId;
        private String username; // 아이디
        private String name; // 이름
        private String email; //이메일
        private String address; //주소
        private Integer role; // 0 -> guest, 1 -> comp

        public GuestDTO(User user) {
            this.userId = user.getId();
            this.username = user.getUsername();
            this.name = user.getName();
            this.email = user.getEmail();
            this.address = user.getAddress();
            this.role = user.getRole();
        }
    }

    @Data
    public static class CompDTO {
        private Integer uerId;
        private String username; // 아이디
        private String name; // 담당자 이름
        private String compname; // 회사명
        private String email; //이메일
        private String ceo; // 기업 대표명
        private Integer role; // 0 -> guest, 1 -> comp

        public CompDTO(User user) {
            this.uerId = user.getId();
            this.username = user.getUsername();
            this.name = user.getName();
            this.compname = user.getCompname();
            this.email = user.getEmail();
            this.ceo = user.getCeo();
            this.role = user.getRole();
        }
    }

    // 메인 홈
    @Data
    public static class MainDTO {
        private List<JobopenDTO> jobopenDTO = new ArrayList<>();

        public MainDTO(List<Jobopen> jobopen) {
            this.jobopenDTO = jobopen.stream().map(jobopen1 -> new JobopenDTO(jobopen1)).toList();
        }

        @Data
        public static class JobopenDTO {
            private Integer userId;
            private String imgFilename;
            ; // 파일 패스
            private Integer jobopenId; //private Integer jobopenId; //
            private String jobopenTitle; //공고제목
            private String compLocation; //
            private String career; //
            private String edu; //
            private String endTime; //

            public JobopenDTO(Jobopen jobopen) {
                this.userId = jobopen.getUser().getId();
                this.imgFilename = jobopen.getUser().getImgFilename();
                this.jobopenId = jobopen.getId();
                this.jobopenTitle = jobopen.getJobopenTitle();
                this.compLocation = jobopen.getCompLocation();
                this.career = jobopen.getCareer();
                this.edu = jobopen.getEdu();
                this.endTime = String.valueOf(jobopen.getEndTime());
            }
        }
    }
}
