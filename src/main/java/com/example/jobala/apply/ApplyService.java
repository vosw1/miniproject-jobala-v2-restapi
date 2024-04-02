package com.example.jobala.apply;

import com.example.jobala._core.errors.apiException.ApiException403;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenJPARepository;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyJPARepository applyJPARepository;
    private final JobopenJPARepository jobopenJPARepository;
    private final ResumeJPARepository resumeJPARepository;


    // 상태수정
    public ApplyResponse.StatusUpdateDTO statusUpdate(ApplyRequest.ApplyStatusUpdateRequestDTO reqDTO, User sessionUser) {
        Apply apply = applyJPARepository.findById(reqDTO.getApplyId())
                .orElseThrow(() -> new ApiException403("해당 정보를 찾을 수 없습니다."));

        apply.setState(reqDTO.getStatus());
        applyJPARepository.save(apply);
        return new ApplyResponse.StatusUpdateDTO(true);
    }

    // 지원 후 저장
    @Transactional
    public ApplyResponse.ApplicationDTO saveAfterApply(ApplyRequest.ApplyRequestDTO reqDTO, User sessionUser) {
        Jobopen jobopen = jobopenJPARepository.findById(reqDTO.getJobopenId())
                .orElseThrow(() -> new ApiException403("공고를 찾을 수 없습니다."));
        Resume resume = resumeJPARepository.findById(reqDTO.getResumeId())
                .orElseThrow(() -> new ApiException403("이력서를 찾을 수 없습니다."));

        applyJPARepository.save(reqDTO.toEntity(resume, jobopen, sessionUser));
        return new ApplyResponse.ApplicationDTO(true);
    }

    // 기업/개인 지원 현황 보기
    public List<?> findApplyByUserId(Integer id, Integer role) {
        if (role == 0) { // 개인
            return applyJPARepository.findApplyGuestByUserId(id);
        } else if (role == 1) { // 기업
            return applyJPARepository.findApplyCompByUserId(id);
        }
        return new ArrayList<>();
    }

    // 기업/개인 포지션 제안 보기
    public List<?> findPositionByUserId(Integer id, Integer role) {
        if (role == 0) { // 개인
            return applyJPARepository.findPositionGuestByUserId(id);
        } else if (role == 1) { // 기업
            return applyJPARepository.findPositionCompByUserId(id);
        }
        return new ArrayList<>();
    }
}