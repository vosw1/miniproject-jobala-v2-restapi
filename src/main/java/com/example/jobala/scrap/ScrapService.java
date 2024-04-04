package com.example.jobala.scrap;

import com.example.jobala._core.errors.apiException.ApiException403;
import com.example.jobala._user.SessionUser;
import com.example.jobala._user.User;
import com.example.jobala._user.UserJPARepository;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenJPARepository;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeJPARepository;
import com.example.jobala.resume.ResumeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapJPARepository scrapJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    private final JobopenJPARepository jobopenJPARepository;
    private final UserJPARepository userJPARepository;

    // 기업이 인재 스크랩
    @Transactional
    public ScrapResponse.CompDTO scrapByComp(ScrapRequest.ScrapDTO reqDTO, SessionUser sessionUser) {
        User user = userJPARepository.findById(sessionUser.getId()).orElseThrow(() -> new ApiException404("해당하는 회원정보를 찾을 수 없습니다."));

        Resume resume = resumeJPARepository.findById(reqDTO.getResumeId())
                .orElseThrow(() -> new ApiException404("스크랩 하려는 이력서를 찾을 수 없습니다."));

        Scrap scrap = scrapJPARepository.findCompScrapByResumeIdAndUserId(reqDTO.getResumeId(), sessionUser.getId())
                .orElse(null);
        if (scrap == null) {
            Scrap scrapResult = scrapJPARepository.save(reqDTO.toEntity(resume, user));
            return new ScrapResponse.CompDTO(scrapResult);
        } else {
            scrapJPARepository.deleteById(scrap.getId());
            return null;
        }
    }

    // 개인이 채용공고 스크랩
    @Transactional
    public ScrapResponse.GuestDTO scrapByGuest(ScrapRequest.ScrapDTO reqDTO, SessionUser sessionUser) {
        User user = userJPARepository.findById(sessionUser.getId()).orElseThrow(() -> new ApiException404("해당하는 회원정보를 찾을 수 없습니다."));

        Jobopen jobopen = jobopenJPARepository.findById(reqDTO.getJobopenId())
                .orElseThrow(() -> new ApiException404("스크랩 하려는 공고를 찾을 수 없습니다."));

        Scrap scrap = scrapJPARepository.findGuestScrapByJobopenIdAndUserId(reqDTO.getJobopenId(), sessionUser.getId())
                .orElse(null);
        if (scrap == null) {
            Scrap scrapResult = scrapJPARepository.save(reqDTO.toEntity(jobopen, user));
            return new ScrapResponse.GuestDTO(scrapResult);
        } else {
            scrapJPARepository.deleteById(scrap.getId());
            return null;
        }
    }

    // 기업이 스크랩한 인재 조회
    public List<ResumeResponse.ScrapDTO> scrapResumeBycomp(SessionUser sessionUser) {
        List<ResumeResponse.ScrapDTO> respDTO = resumeJPARepository.findByUserIdJoinScrap(sessionUser.getId());
        return respDTO;
    }

    // 개인이 스크랩한 채용공고 조회
    public List<JobopenResponse.ScrapDTO> scrapJobopenByGuest(SessionUser sessionUser) {
        List<JobopenResponse.ScrapDTO> respDTO = jobopenJPARepository.findByUserIdJoinScrap(sessionUser.getId());
        return respDTO;
    }
}
