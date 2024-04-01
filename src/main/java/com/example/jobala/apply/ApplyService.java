package com.example.jobala.apply;

import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenJPARepository;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyQueryRepository applyQueryRepository;
    private final ApplyJPARepository applyJPARepository;
    private final JobopenJPARepository jobopenJPARepository;
    private final ResumeJPARepository resumeJPARepository;


    // 상태수정
    @Transactional
    public ApplyResponse.StatusUpdateDTO statusUpdate(Integer applyId, String status) throws Exception404 {
        Apply apply = applyJPARepository.findById(applyId)
                .orElseThrow(() -> new Exception404("해당 정보를 찾을 수 없습니다."));
        apply.setState(status);
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

    // 기업 사용자
    public List<ApplyResponse.CompPositionDTO> findApplyCompByUserId(Integer id) {
        List<Apply> applyList = applyJPARepository.findByUserId(id);
        return applyList.stream()
                .map(ApplyResponse.CompPositionDTO::new)
                .collect(Collectors.toList());
    }

    // 개인 사용자
    public List<ApplyResponse.GuestPositionDTO> findJobOpenByUserId(Integer id) {
        List<Apply> applyList = applyJPARepository.findByUserId(id);
        return applyList.stream()
                .map(ApplyResponse.GuestPositionDTO::new)
                .collect(Collectors.toList());
    }

    // 사용자 역할에 따른 통합 조회 및 변환 로직
    public ResponseEntity<ApiUtil<?>> findPositionFormByUserId(User sessionUser) {
        if (sessionUser.getRole() == 1) {
            List<ApplyResponse.CompPositionDTO> respDTO = findApplyCompByUserId(sessionUser.getId());
            return ResponseEntity.ok(new ApiUtil<>(respDTO));
        } else {
            List<ApplyResponse.GuestPositionDTO> respDTO = findJobOpenByUserId(sessionUser.getId());
            return ResponseEntity.ok(new ApiUtil<>(respDTO));
        }
    }

        public Object findApplyGuestByUserId (Integer id, Integer role){

            List<Apply> applyList = applyJPARepository.findByUserId(id);
            return applyList.stream().map(apply -> new ApplyResponse.GuestApplyDTO(apply)).toList();
        }
    }
