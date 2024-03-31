package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import com.example.jobala.resume.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.*;

public class JobopenResponse {
    @AllArgsConstructor
    @Data
    public static class ListDTO {
        private Integer id;
        private String jobopenTitle;
        private String compLocation;
        private String career;
        private String edu;
        private String imgFilename;
    }

    @Data
    public static class SaDTO{
        private Integer userId;
        private Integer id;
        private String edu;
        private String jobopenTitle;
        private String career;
        private String jobType;
        private String salary;
        private String hopeJob;
        private String compLocation;
        private java.sql.Date endTime;
        private List<String> skills = new ArrayList<>(); //내용

        public SaDTO(Jobopen jobopen, User sessionUser) {
            this.userId = sessionUser.getId();
            this.id = jobopen.getId();
            this.edu = jobopen.getEdu();
            this.jobopenTitle = jobopen.getJobopenTitle();
            this.career = jobopen.getCareer();
            this.jobType = jobopen.getJobType();
            this.salary = jobopen.getSalary();
            this.hopeJob = jobopen.getHopeJob();
            this.compLocation = jobopen.getCompLocation();
            this.endTime = jobopen.getEndTime();
            this.skills = Arrays.asList(jobopen.getSkills().split(",")); // 쉼표로 구분된 기술들을 리스트로 변환
        }
    }

    @Data
    public static class DTO {
        private Integer userId;
        private List<JobopenDTO> jobopenDTO = new ArrayList<>();

        public DTO(Integer userId, List<Jobopen> jobopen) {
            this.userId = userId;
            this.jobopenDTO = jobopen.stream().map(JobopenDTO::new).toList();
        }

        @Data
        public class JobopenDTO {
            private Integer id;
            private Integer role; // 역할 0 -> guest, 1 -> comp
            private String jobopenTitle; //공고제목
            private Integer applyCount;

            public JobopenDTO(Jobopen jobopen) {
                this.id = jobopen.getId();
                this.role = jobopen.getRole();
                this.jobopenTitle = jobopen.getJobopenTitle();
            }
        }
    }

    @AllArgsConstructor
    @Data
    public static class DetailDTO {
        private Integer id;
        private String jobopenTitle;
        private String career;
        private String edu;
        private String jobType;
        private String salary;
        private String compLocation;
        private String hopeJob;
        private String skills;
        private Date endTime; // 마감일
        private boolean isScrap;
        private boolean isGuestScrap;
        private UserDTO userDTO;
        private List<ResumeDTO> applyResumeList = new ArrayList<>();

        public DetailDTO(Jobopen jobopen, User sessionUser, List<Resume> resumeList) {
            this.id = jobopen.getId();
            this.jobopenTitle = jobopen.getJobopenTitle();
            this.career = jobopen.getCareer();
            this.edu = jobopen.getEdu();
            this.jobType = jobopen.getJobType();
            this.salary = jobopen.getSalary();
            this.compLocation = jobopen.getCompLocation();
            this.hopeJob = jobopen.getHopeJob();
            this.endTime = jobopen.getEndTime();
            this.skills = jobopen.getSkills();
            this.isScrap = false;
            this.isGuestScrap = false;
            this.userDTO = new UserDTO(jobopen.getUser());

            // 개인 지원하기 - 모달창 이력서 목록
            this.applyResumeList = resumeList.stream().map(r -> new ResumeDTO(r)).toList();
            if (sessionUser != null) {
                if (sessionUser.getRole() == 0) {
                    this.isGuestScrap = true;
                }
            }
        }

        public DetailDTO(Jobopen jobopen, User sessionUser) {
            this.id = jobopen.getId();
            this.jobopenTitle = jobopen.getJobopenTitle();
            this.career = jobopen.getCareer();
            this.edu = jobopen.getEdu();
            this.jobType = jobopen.getJobType();
            this.salary = jobopen.getSalary();
            this.compLocation = jobopen.getCompLocation();
            this.hopeJob = jobopen.getHopeJob();
            this.skills = jobopen.getSkills();
            this.isScrap = false;
            this.isGuestScrap = false;

            this.userDTO = new UserDTO(jobopen.getUser());

            if (sessionUser != null) {
                if (sessionUser.getRole() == 0) {
                    this.isGuestScrap = true;
                }
            }
        }

        @Data
        public class ResumeDTO {
            private String resumeTitle;
            private String edu;
            private String career;

            public ResumeDTO(Resume resume) {
                this.resumeTitle = resume.getResumeTitle();
                this.edu = resume.getEdu();
                this.career = resume.getCareer();
            }
        }

        @Data
        public class UserDTO {
            private Integer userId;
            private String compname;
            private String imgFilename;

            public UserDTO(User user) {
                this.userId = user.getId();
                this.compname = user.getCompname();
                this.imgFilename = user.getImgFilename();
            }
        }
    }

    @AllArgsConstructor
    @Data
    public static class SearchDTO {
        private Integer id;
        private String jobopenTitle;
        private String compLocation;
        private String career;
        private String edu;
        private Date endTime;
    }

    @AllArgsConstructor
    @Data
    public static class ScrapDTO {
        private int id;
        private String compname;
        private String jobopenTitle;
        private String career;

        public ScrapDTO(Jobopen jobopen) {
            this.id = jobopen.getId();
            this.compname = jobopen.getUser().getCompname();
            this.jobopenTitle = jobopen.getJobopenTitle();
            this.career = jobopen.getCareer();
        }
    }

    // update시 체크되도록하는 DTO
    @Data
    @AllArgsConstructor
    public static class CheckBoxDTO {
        private Boolean java;
        private Boolean javaScript;
        private Boolean spring;
        private Boolean html;
        private Boolean jquery;
        private Boolean mysql;

        public CheckBoxDTO(List<String> skillList) {
            this.java = skillList.contains("Java");
            this.javaScript = skillList.contains(" JavaScript");
            this.spring = skillList.contains(" Spring");
            this.html = skillList.contains(" HTML");
            this.jquery = skillList.contains(" jQuery");
            this.mysql = skillList.contains(" MySQL");
        }
    }
}




