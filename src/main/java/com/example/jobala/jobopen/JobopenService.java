package com.example.jobala.jobopen;

import com.example.jobala._core.errors.apiException.ApiException403;
import com.example.jobala._user.SessionUser;
import com.example.jobala._user.User;
import com.example.jobala._user.UserJPARepository;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeJPARepository;
import com.example.jobala.scrap.Scrap;
import com.example.jobala.scrap.ScrapJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobopenService {

    private final JobopenJPARepository jobopenJPARepository;
    private final ScrapJPARepository scrapJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    private final UserJPARepository userJPARepository;

    // 공고목록보기
    public List<Jobopen> jobopenFindAll() {
        return jobopenJPARepository.findAll();
    }

    // 공고등록임
    @Transactional
    public JobopenResponse.SaveDTO jobopenSave(JobopenRequest.SaveDTO reqDTO, SessionUser sessionUser) {
        User user = userJPARepository.findById(sessionUser.getId()).orElseThrow(() -> new ApiException403("해당하는 회원정보를 찾을 수 없습니다."));

        Jobopen jobopen = jobopenJPARepository.save(reqDTO.toEntity(user));
        return new JobopenResponse.SaveDTO(jobopen, user);
    }

    // 공고삭제
    @Transactional
    public Jobopen jobopenDelete(Integer jobopenId, Integer sessionUserId) {
        Jobopen jobopen = jobopenJPARepository.findById(jobopenId)
                .orElseThrow(() -> new ApiException403("공고를 찾을 수 없습니다."));

        if (sessionUserId != jobopen.getUser().getId()) {
            throw new ApiException403("공고를 삭제할 권한이 없습니다.");
        }
        jobopenJPARepository.deleteById(jobopenId);
        return jobopen;
    }

    // 공고수정하기
    @Transactional
    public JobopenResponse.UpdateDTO jobopenUpdate(int jobOpenId, SessionUser sessionUser, JobopenRequest.UpdateDTO reqDTO) {
        //1.조회 및 예외 처리
        Jobopen jobopen = jobopenJPARepository.findById(jobOpenId)
                .orElseThrow(() -> new ApiException403("공고를 찾을 수 없습니다."));
        //2.권한 처리
        if (sessionUser.getId() != jobopen.getUser().getId()) {
            throw new ApiException403("공고를 수정할 권한이 없습니다.");
        }
        //3.공고 수정
        jobopen.setJobopenUpdate(reqDTO);
        return new JobopenResponse.UpdateDTO(jobopen);
    }

    // 공고보기
    public JobopenResponse.DetailDTO findJobopenById(Integer jobopenId, SessionUser sessionUser) {
        User user = userJPARepository.findById(sessionUser.getId()).orElseThrow(() -> new ApiException403("해당하는 회원정보를 찾을 수 없습니다."));

        Jobopen jobopen = jobopenJPARepository.findByJobopenIdWithUser(jobopenId)
                .orElseThrow(() -> new ApiException403("공고를 찾을 수 없습니다"));

        List<String> skills = Arrays.stream(jobopen.getSkills().replaceAll("[\\[\\]\"]", "").split(",")).toList();
        String skillsString = String.join(", ", skills);

        // isScrap
        if (sessionUser != null) {
            // 모달 공고 목록 조회
            List<Resume> applyResumeList = resumeJPARepository.findByUserId(sessionUser.getId());
            // 스크랩 했는지 안했는지 조회
            Optional<Scrap> scrap = scrapJPARepository.findGuestScrapByJobopenIdAndUserId(jobopenId, sessionUser.getId());
            JobopenResponse.DetailDTO respDTO = new JobopenResponse.DetailDTO(jobopen, user, applyResumeList);
            respDTO.setScrap(scrap.isPresent());
            respDTO.setSkills(skillsString);
            return respDTO;
        }
        JobopenResponse.DetailDTO respDTO = new JobopenResponse.DetailDTO(jobopen, user);
        respDTO.setSkills(skillsString);
        return respDTO;
    }

    //TODO: 이건 쓰는 건가? 찬혁?
    public JobopenResponse.CheckBoxDTO getCheckedSkills(Integer id) {
        Jobopen jobopen = jobopenJPARepository.findById(id).orElseThrow(() -> new ApiException403("공고를 찾을 수 없습니다."));
        String skillsStr = jobopen.getSkills();
        skillsStr = skillsStr.substring(1, skillsStr.length() - 1).trim();
        List<String> skills = Arrays.asList(skillsStr.split(","));
        return new JobopenResponse.CheckBoxDTO(skills);
    }
}
