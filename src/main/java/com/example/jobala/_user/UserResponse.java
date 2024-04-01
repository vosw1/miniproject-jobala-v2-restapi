package com.example.jobala._user;


import com.example.jobala.jobopen.Jobopen;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class UserResponse {

    @Data
    public static class CompProfile {
        private Integer id;
        private String imgFilename;
        private String name;
        private String compname;
        private String email;

        public CompProfile(User user) {
            this.id = user.getId();
            this.imgFilename = user.getImgFilename();
            this.name = user.getName();
            this.email = user.getEmail();
        }
    }

    @Data
    public static class GuestProfile {
        private Integer id;
        private String name;
        private String email;

        public GuestProfile(User user) {
            this.id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
        }
    }

    @Data
    public static class JoinDTO {
        private GuestDTO guestDTO; // 개인 회원가입
        private CompDTO compDTO; // 기업 회원가입

        public JoinDTO(User user) {
            this.guestDTO = new GuestDTO(user);
            this.compDTO = new CompDTO(user);
        }

        @Data
        public static class GuestDTO {
            private Integer id;
            private String username; // 아이디
            private String name; // 이름
            private String email; //이메일
            private String address; //주소
            private Integer role; // 0 -> guest, 1 -> comp

            public GuestDTO(User user) {
                this.id = user.getId();
                this.username = user.getUsername();
                this.name = user.getName();
                this.email = user.getEmail();
                this.address = user.getAddress();
                this.role = user.getRole();
            }
        }

        @Data
        public static class CompDTO {
            private Integer id;
            private String username; // 아이디
            private String name; // 담당자 이름
            private String compname; // 회사명
            private String email; //이메일
            private String ceo; // 기업 대표명
            private Integer role; // 0 -> guest, 1 -> comp

            public CompDTO(User user) {
                this.id = user.getId();
                this.username = user.getUsername();
                this.name = user.getName();
                this.compname = user.getCompname();
                this.email = user.getEmail();
                this.ceo = user.getCeo();
                this.role = user.getRole();
            }
        }
    }


    @Data
    public static class MainDTO {
        private List<JobopenDTO> jobopenDTO = new ArrayList<>();

        public MainDTO(List<Jobopen> jobopen) {
            this.jobopenDTO = jobopen.stream().map(jobopen1 -> new JobopenDTO(jobopen1)).toList();
        }

        @Data
        public static class JobopenDTO {
            private Integer userId;
            private String imgFilename;; // 파일 패스
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


    @Data
    public static class LoginResponseDTO {
        private User user;
        private Boolean isCheck;

        public LoginResponseDTO(User user, Boolean isCheck) {
            this.user = user;
            this.isCheck = isCheck;
        }
    }
}
