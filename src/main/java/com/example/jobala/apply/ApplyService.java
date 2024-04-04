package com.example.jobala.apply;

import com.example.jobala._core.errors.apiException.ApiException403;
import com.example.jobala._core.errors.apiException.ApiException404;
import com.example.jobala._user.SessionUser;
import com.example.jobala._user.User;
import com.example.jobala._user.UserJPARepository;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenJPARepository;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyJPARepository applyJPARepository;
    private final JobopenJPARepository jobopenJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    private final UserJPARepository userJPARepository;


    // 상태수정
    public ApplyResponse.StatusUpdateDTO statusUpdate(ApplyRequest.ApplyStatusUpdateRequestDTO reqDTO, SessionUser sessionUser) {
        Apply apply = applyJPARepository.findById(reqDTO.getApplyId())
                .orElseThrow(() -> new ApiException404("해당 지원정보를 찾을 수 없습니다."));

        apply.setState(reqDTO.getStatus());
        applyJPARepository.save(apply);

        return new ApplyResponse.StatusUpdateDTO(apply);
    }

    // 지원 후 저장
    @Transactional
    public ApplyResponse.ApplicationDTO saveAfterApply(ApplyRequest.ApplyRequestDTO reqDTO, SessionUser sessionUser) {
        Jobopen jobopen = jobopenJPARepository.findById(reqDTO.getJobopenId())
                .orElseThrow(() -> new ApiException404("공고를 찾을 수 없습니다."));

        Resume resume = resumeJPARepository.findById(reqDTO.getResumeId())
                .orElseThrow(() -> new ApiException404("이력서를 찾을 수 없습니다."));

        User user = userJPARepository.findById(sessionUser.getId())
                .orElseThrow(() -> new ApiException404("해당하는 회원정보를 찾을 수 없습니다."));

        Apply apply = applyJPARepository.save(reqDTO.toEntity(resume, jobopen, user));
        return new ApplyResponse.ApplicationDTO(apply);
    }

    // 개인이 보는 포지션 제안 현황
    public List<ApplyResponse.GuestPositionDTO> findPositionGuestByUserId(int userId) {
        return applyJPARepository.findPositionGuestByUserId(userId);
    }

    // 기업이 포지션 제안한 현황
    public List<ApplyResponse.CompPositionDTO> findPositionCompByUserId(int userId) {
        return applyJPARepository.findPositionCompByUserId(userId);
    }

    // 개인이 지원한 이력서 현황
    public List<ApplyResponse.GuestApplyDTO> findApplyGuestByUserId(int userId) {
        return applyJPARepository.findApplyGuestByUserId(userId);
    }

    // 기업이 보는 지원자 현황
    public List<ApplyResponse.CompApplyDTO> findApplyCompByUserId(int userId) {
        return applyJPARepository.findApplyCompByUserId(userId);
    }
}