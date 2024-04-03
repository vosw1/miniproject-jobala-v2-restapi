package com.example.jobala.comp;

import com.example.jobala._core.errors.apiException.ApiException400;
import com.example.jobala._core.errors.apiException.ApiException403;
import com.example.jobala._user.*;
import com.example.jobala.apply.ApplyJPARepository;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.ResumeJPARepository;
import com.example.jobala.resume.ResumeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompService {
    private final UserJPARepository userJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    private final CompQueryRepository compQueryRepository;
    private final CompJPARepository compJPARepository;
    private final ApplyJPARepository applyJPARepository;

    //기업 - 인재채용 검색필터
    public List<ResumeResponse.ScoutListDTO> searchResumes(String skills, CompRequest.SearchDTO resDTO) {
        return compQueryRepository.findAll(skills, resDTO);
    }

    // 기업 - 인재채용 목록, 최신 1건 이력서만 가져오기
    public List<ResumeResponse.ScoutListDTO> listAllResumes() {
        List<ResumeResponse.ScoutListDTO> resumes = compQueryRepository.findResumeAll();
        if (resumes.isEmpty()) {
            throw new ApiException403("이력서가 없습니다.");
        }
        return resumes;
    }

    // 기업 - 마이페이지 공고 관리
    public List<JobopenResponse.MngDTO> compJobopenMng(SessionUser sessionUser) {
        List<Jobopen> temp = compQueryRepository.findJobopenByWithUserId(sessionUser.getId());
        List<JobopenResponse.MngDTO> jobopenList = temp.stream().map(jobopen -> new JobopenResponse.MngDTO(sessionUser.getId(), temp)).toList();

        jobopenList.forEach(dto -> dto.getJobopenDTO().forEach(jobopenDTO -> {
            int count = applyJPARepository.countJobopenApplyById(jobopenDTO.getId());
            jobopenDTO.setApplyCount(count);
        }));
        return jobopenList;
    }

    // 기업 - 프로필관리
    public UserResponse.GuestProfile compProfile(SessionUser sessionUser) {
        User user = userJPARepository.findById(sessionUser.getId()).orElseThrow(() -> new ApiException403("유저를 찾을 수 없습니다."));

        return new UserResponse.GuestProfile(user);
    }

    //기업 - 프로필업데이트
    @Transactional
    public UserResponse.CompProfile compUpdateProfile(UserRequest.UserUpdateDTO reqDTO, SessionUser sessionUser) {
        User user = compJPARepository.findById(sessionUser.getId()).orElseThrow(() -> new ApiException403("수정할 프로필이 없습니다."));

        try {
            //베이스 64로 들어오는 문자열을 바이트로 디코딩하기
            byte[] decodedBytes = Base64.getDecoder().decode(reqDTO.getImgFilename().getBytes());
            String imageUUID = UUID.randomUUID() + "_" + reqDTO.getImgTitle();

            // 이미지 파일의 저장 경로 설정
            Path imgPath = Paths.get("./image/" + imageUUID);
            Files.write(imgPath, decodedBytes);
            String webImgPath = imgPath.toString().replace("\\", "/");
            webImgPath = webImgPath.substring(webImgPath.lastIndexOf("/") + 1);
            user.setProfileUpdateDTO(reqDTO, webImgPath);

        } catch (IOException e) {
            throw new ApiException400("올바른 저장 경로를 찾지 못했습니다.");
        }
        return new UserResponse.CompProfile(user);
    }

}
