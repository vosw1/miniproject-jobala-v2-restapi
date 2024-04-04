package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import com.example.jobala.resume.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class JobopenResponse {

    @Data//기업 - 마이페이지 공고 관리 DTO
    public static class MngDTO {
        private Integer userId;
        private List<JobopenDTO> jobopenDTO = new ArrayList<>();

        public MngDTO(Integer userId, List<Jobopen> jobopen) {
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
                this.applyCount = jobopen.getId();

            }
        }
    }

    //공고 수정
    @Data
    public static class UpdateDTO {
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

        public UpdateDTO(Jobopen jobopen) {
            this.id = jobopen.getId();
            this.jobopenTitle = jobopen.getJobopenTitle();
            this.career = jobopen.getCareer();
            this.edu = jobopen.getEdu();
            this.jobType = jobopen.getJobType();
            this.salary = jobopen.getSalary();
            this.compLocation = jobopen.getCompLocation();
            this.hopeJob = jobopen.getHopeJob();
            this.skills = jobopen.getSkills();
            this.endTime = jobopen.getEndTime();
        }
    }

    //채용공고 검색
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ListDTO {
        private Integer compId;
        private String jobopenTitle;
        private String compLocation;
        private String career;
        private String edu;
        private String imgFilename;
    }


    //공고 쓰기 응답 dto
    @Data
    public static class SaveDTO {
        private Integer userId;
        private Integer jobopenId;
        private String edu;
        private String jobopenTitle;
        private String career;
        private String jobType;
        private String salary;
        private String hopeJob;
        private String compLocation;
        private java.sql.Date endTime;
        private List<String> skills = new ArrayList<>(); //내용

        public SaveDTO(Jobopen jobopen, User user) {
            this.userId = user.getId();
            this.jobopenId = jobopen.getId();
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

    //공고 상세보기
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

        public DetailDTO(Jobopen jobopen, User user, List<Resume> resumeList) {
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
            if (user != null) {
                if (user.getRole() == 0) {
                    this.isGuestScrap = true;
                }
            }
        }

        public DetailDTO(Jobopen jobopen, User user) {
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

            if (user != null) {
                if (user.getRole() == 0) {
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
            private Integer id;
            private String compname;
            private String imgFilename;

            public UserDTO(User user) {
                this.id = user.getId();
                this.compname = user.getCompname();
                this.imgFilename = user.getImgFilename();
            }
        }
    }

    //공고 스크랩
    @AllArgsConstructor
    @Data
    public static class ScrapDTO {
        private int jobopenId;
        private String compname;
        private String jobopenTitle;
        private String career;

        public ScrapDTO(Jobopen jobopen) {
            this.jobopenId = jobopen.getId();
            this.compname = jobopen.getUser().getCompname();
            this.jobopenTitle = jobopen.getJobopenTitle();
            this.career = jobopen.getCareer();
        }
    }

}




