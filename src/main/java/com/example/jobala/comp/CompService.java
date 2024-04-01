package com.example.jobala.comp;

import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._user.User;
import com.example.jobala._user.UserJPARepository;
import com.example.jobala._user.UserResponse;
import com.example.jobala.apply.ApplyJPARepository;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.ResumeJPARepository;
import com.example.jobala.resume.ResumeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
            throw new Exception404("이력서가 없습니다.");
        }
        return resumes;
    }

    // 기업 - 마이페이지 공고 관리
    public List<JobopenResponse.MngDTO> compJobopenMng(Integer sessionUserId) {
        //공고와 유저를 조인해서 가져온 공고 리스트
        List<Jobopen> temp = compQueryRepository.findJobopenByWithUserId(sessionUserId);
        List<JobopenResponse.MngDTO> jobopenList = temp.stream()
                .map(jobopen -> new JobopenResponse.MngDTO(sessionUserId, temp)).toList();

        jobopenList.forEach(dto ->
                dto.getJobopenDTO().forEach(jobopenDTO -> {
                    int count = applyJPARepository.countJobopenApplyById(jobopenDTO.getId());
                    jobopenDTO.setApplyCount(count);
                })
        );
        return jobopenList;
    }

    // 기업 - 프로필관리
    public UserResponse.GuestProfile compProfile(Integer userId) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new Exception404("유저를 찾을 수 없습니다."));

        return new UserResponse.GuestProfile(user);
    }


    //기업 - 프로필업데이트
    @Transactional
    public UserResponse.CompProfile compUpdateProfile(CompRequest.CompProfileUpdateDTO reqDTO, User sessionUser) {
        User user = compJPARepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("수정할 프로필이 없습니다."));
        user.setCompProfileUpdateDTO(reqDTO);

        return new UserResponse.CompProfile(user);
    }

}
