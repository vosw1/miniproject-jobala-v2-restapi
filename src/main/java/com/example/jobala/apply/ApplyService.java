package com.example.jobala.apply;

import com.example.jobala._core.errors.apiException.ApiException403;
import com.example.jobala._user.SessionUser;
import com.example.jobala._user.User;
import com.example.jobala._user.UserJPARepository;
import com.example.jobala.apply.Apply;
import com.example.jobala.apply.ApplyJPARepository;
import com.example.jobala.apply.ApplyRequest;
import com.example.jobala.apply.ApplyResponse;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenJPARepository;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyJPARepository applyJPARepository;
    private final JobopenJPARepository jobopenJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    private final UserJPARepository userJPARepository;



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
    public ApplyResponse.ApplicationDTO saveAfterApply(ApplyRequest.ApplyRequestDTO reqDTO, SessionUser sessionUser) {
        Jobopen jobopen = jobopenJPARepository.findById(reqDTO.getJobopenId())
                .orElseThrow(() -> new ApiException403("공고를 찾을 수 없습니다."));

        Resume resume = resumeJPARepository.findById(reqDTO.getResumeId())
                .orElseThrow(() -> new ApiException403("이력서를 찾을 수 없습니다."));
        User user = userJPARepository.findById(sessionUser.getId()).orElseThrow(() -> new ApiException403("회워정보를 찾을 수 없습니다."));
        Apply apply = applyJPARepository.save(reqDTO.toEntity(resume, jobopen, user));
        return new ApplyResponse.ApplicationDTO(apply);
    }

    // 개인의 포지션 제안 현황 조회
    public List<ApplyResponse.GuestPositionDTO> findPositionGuestByUserId(int userId) {
        return applyJPARepository.findPositionGuestByUserId(userId);
    }

    // 기업의 포지션 제안 현황 조회
    public List<ApplyResponse.CompPositionDTO> findPositionCompByUserId(int userId) {
        return applyJPARepository.findPositionCompByUserId(userId);
    }

    // 개인의 이력서 지원 현황 조회
    public List<ApplyResponse.GuestApplyDTO> findApplyGuestByUserId(int userId) {
        return applyJPARepository.findApplyGuestByUserId(userId);
    }

    // 기업의 이력서 지원 현황 조회
    public List<ApplyResponse.CompApplyDTO> findApplyCompByUserId(int userId) {
        return applyJPARepository.findApplyCompByUserId(userId);
    }
}