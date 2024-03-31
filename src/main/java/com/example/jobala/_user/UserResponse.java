package com.example.jobala._user;


import com.example.jobala.jobopen.Jobopen;
import lombok.Data;

public class UserResponse {


    @Data
    public static class GuestProfile {
        private Integer id;
        private String imgFilename;
        private String name;
        private String password;
        private String phone;
        private String email;
        private String address;

        public GuestProfile(User user) {
            this.id = user.getId();
            this.imgFilename = user.getImgFilename();
            this.name = user.getName();
            this.password = user.getPassword();
            this.phone = user.getPhone();
            this.email = user.getEmail();
            this.address = user.getAddress();
        }
    }

    @Data
    public static class JoinDTO {
        private GuestDTO guestDTO; // 개인 회원가입
        private CompDTO compDTO; // 기업 회원가입


        public JoinDTO(GuestDTO guestDTO, CompDTO compDTO) {
            this.guestDTO = guestDTO;
            this.compDTO = compDTO;
        }

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
