package com.example.jobala.resume;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ResumeResponse {
    @Data
    public static class ASaveDTO {
        private Integer userId;
        private Integer Id;
        private String resumeTitle;
        private String hopeJob;
        private String career;
        private String license;
        private String content;
        private String edu;

        private List<String> skills = new ArrayList<>();

        public ASaveDTO(Resume resume, User sessionUser) {
            this.userId = sessionUser.getId();
            this.Id = resume.getId();
            this.resumeTitle = resume.getResumeTitle();
            this.hopeJob = resume.getHopeJob();
            this.career = resume.getCareer();
            this.license = resume.getLicense();
            this.content = resume.getContent();
            this.edu = resume.getEdu();
            this.skills = Arrays.asList(resume.getSkills());
        }
    }



    @Data
    public static class MngDTO {
        private Integer id;
        private List<ResumeDTO> resume = new ArrayList<>();

        public MngDTO(Integer userId, List<Resume> resumeList) {
            this.id = userId;
            this.resume = resumeList.stream().map(ResumeDTO::new).toList();
        }

        @Data
        public static class ResumeDTO {
            private Integer id;
            private String resumeTitle; // 공고 제목

            public ResumeDTO(Resume resume) {
                this.id = resume.getId();
                this.resumeTitle = resume.getResumeTitle();
            }
        }
    }

    @AllArgsConstructor
    @Data
    public static class ScrapDTO {
        private int id;
        private String name;
        private String resumeTitle;
        private String career;
        private String edu;

        public ScrapDTO(Resume resume) {
            this.id = resume.getId();
            this.name = resume.getUser().getName();
            this.resumeTitle = resume.getResumeTitle();
            this.career = resume.getCareer();
            this.edu = resume.getEdu();
        }
    }

    // update시 체크되도록하는 DTO
    @Data
    @AllArgsConstructor
    public static class CheckBoxDTO {
        private Boolean java;
        private Boolean JavaScript;
        private Boolean spring;
        private Boolean html;
        private Boolean jquery;
        private Boolean mysql;

        public CheckBoxDTO(List<String> skillList) {
            this.java = skillList.contains("Java");
            this.JavaScript = skillList.contains(" JavaScript");
            this.spring = skillList.contains(" Spring");
            this.html = skillList.contains(" HTML");
            this.jquery = skillList.contains(" jQuery");
            this.mysql = skillList.contains(" MySQL");
        }
    }


    @AllArgsConstructor
    @Data
    public static class ScoutListDTO {
        private Integer id; //이력서 아이디
        private String username;
        private String resumeTitle; //이력서제목
        private String edu; //학력
        private String career; //경력
        private String imgFilename;

    }


    @AllArgsConstructor
    @Data
    public static class DetailDTO {
        private Integer id;
        private String resumeTitle;
        private String hopeJob;
        private String career;
        private String license;
        private String content;
        private String edu;
        private String skills;
        private boolean isScrap;
        private boolean isGuestScrap;
        private UserDTO userDTO;
        private List<JobopenDTO> applyJobopenList = new ArrayList<>();

        public DetailDTO(Resume resume, User sessionUser, List<Jobopen> jobopenList) {
            this.id = resume.getId();
            this.resumeTitle = resume.getResumeTitle();
            this.hopeJob = resume.getHopeJob();
            this.career = resume.getCareer();
            this.license = resume.getLicense();
            this.content = resume.getContent();
            this.edu = resume.getEdu();
            this.skills = resume.getSkills();
            this.isGuestScrap = false;
            this.isScrap = false;

            this.userDTO = new UserDTO(resume.getUser());

            //기업 제안하기 - 모달창 공고 목록
            this.applyJobopenList = jobopenList.stream().map(j -> new JobopenDTO(j)).toList();

            // 회사만 이력서를 스크랩 할 수 있다.
            if (sessionUser != null) {
                if (sessionUser.getRole() == 1) {
                    this.isGuestScrap = true;
                }
            }
        }

        @Data
        public class JobopenDTO {
            private String jobopenTitle;
            private String hopeJob;
            private String career;

            public JobopenDTO(Jobopen jobopen) {
                this.jobopenTitle = jobopen.getJobopenTitle();
                this.hopeJob = jobopen.getHopeJob();
                this.career = jobopen.getCareer();
            }
        }

        @Data
        public class UserDTO {
            private Integer userId;
            private String imgFilename;
            private String name;
            private Date age;
            private String email;
            private String address;

            public UserDTO(User user) {
                this.userId = user.getId();
                this.imgFilename = user.getImgFilename();
                this.name = user.getName();
                this.age = user.getAge();
                this.email = user.getEmail();
                this.address = user.getAddress();
            }
        }
    }

    @AllArgsConstructor
    @Data
    public static class UpdateDTO {
        private Integer id;
        private String resumeTitle;
        private String hopeJob;
        private String career;
        private String license;
        private String content;
        private String edu;
        private String skills;

        public UpdateDTO(Resume resume) {
            this.id = resume.getId();
            this.resumeTitle = resume.getResumeTitle();
            this.hopeJob = resume.getHopeJob();
            this.career = resume.getCareer();
            this.license = resume.getLicense();
            this.content = resume.getContent();
            this.edu = resume.getEdu();
            this.skills = resume.getSkills();
        }
    }
}
