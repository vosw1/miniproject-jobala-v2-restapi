package com.example.jobala.comp;

import com.example.jobala._core.errors.apiException.ApiException400;
import com.example.jobala._core.errors.apiException.ApiException404;
import com.example.jobala._core.utill.UpdateProfileUtil;
import com.example.jobala._user.*;
import com.example.jobala.apply.ApplyJPARepository;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.ResumeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompService {
    private final UserJPARepository userJPARepository;
    private final CompQueryRepository compQueryRepository;
    private final CompJPARepository compJPARepository;
    private final ApplyJPARepository applyJPARepository;
    private final UpdateProfileUtil updateProfileUtil;

    //기업 - 인재채용 검색필터
    public List<ResumeResponse.ScoutListDTO> searchResumes(String skills, CompRequest.SearchDTO resDTO) {
        return compQueryRepository.findAll(skills, resDTO);
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
        User user = userJPARepository.findById(sessionUser.getId()).orElseThrow(() -> new ApiException404("유저를 찾을 수 없습니다."));

        return new UserResponse.GuestProfile(user);
    }

    //기업 - 프로필업데이트
    @Transactional
    public UserResponse.CompProfile compUpdateProfile(UserRequest.UserUpdateDTO reqDTO, SessionUser sessionUser) {
        User user = compJPARepository.findById(sessionUser.getId()).orElseThrow(() -> new ApiException404("수정할 프로필이 없습니다."));

        try {
            String imgFilename = reqDTO.getImgFilename(); // 이미지 파일 이름 가져오기
            String imgTitle = reqDTO.getImgTitle(); // 이미지 타이틀 가져오기
            updateProfileUtil.fileUpdate(imgFilename, imgTitle); // 파일 업데이트 수행
        } catch (IOException e) {
            throw new ApiException400("올바른 저장 경로를 찾지 못했습니다.");
        }
        return new UserResponse.CompProfile(user);

    }
}
