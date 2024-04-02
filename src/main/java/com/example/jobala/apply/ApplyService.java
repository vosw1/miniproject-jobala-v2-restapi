package com.example.jobala.apply;

import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._user.User;
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


    // 상태수정
    public ApplyResponse.StatusUpdateDTO statusUpdate(ApplyRequest.ApplyStatusUpdateRequestDTO reqDTO, User sessionUser) {
        Apply apply = applyJPARepository.findById(reqDTO.getApplyId())
                .orElseThrow(() -> new Exception404("해당 정보를 찾을 수 없습니다."));

        apply.setState(reqDTO.getStatus());
        applyJPARepository.save(apply);
        return new ApplyResponse.StatusUpdateDTO(true);
    }

    // 지원 후 저장
    @Transactional
    public ApplyResponse.ApplicationDTO saveAfterApply(ApplyRequest.ApplyRequestDTO reqDTO, User sessionUser) throws Exception404 {
        Jobopen jobopen = jobopenJPARepository.findById(reqDTO.getJobopenId())
                .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다."));
        Resume resume = resumeJPARepository.findById(reqDTO.getResumeId())
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다."));

        applyJPARepository.save(reqDTO.toEntity(resume, jobopen, sessionUser));
        return new ApplyResponse.ApplicationDTO(true);
    }

    // TODO: 이름
    public List<ApplyResponse.GuestApplyDTO> findApplyGuestByUserId(Integer id, Integer role) {
        List<ApplyResponse.GuestApplyDTO> applyList = applyJPARepository.findApplyGuestByUserId(id);
        return applyList;
    }

    // TODO: 이름
    public List<ApplyResponse.GuestPositionDTO> findPositionGuestByUserId(Integer id, Integer role) {
        List<ApplyResponse.GuestPositionDTO> positionList = applyJPARepository.findPositionGuestByUserId(id);
        return positionList;
    }
}
