package com.example.jobala.scrap;

import com.example.jobala._core.errors.apiException.ApiException403;

import com.example.jobala._user.User;
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

    // 회사가스크랩
    @Transactional
    public ScrapResponse.CompDTO scrapByComp(ScrapRequest.ScrapDTO reqDTO, User sessionUser) {
        Resume resume = resumeJPARepository.findById(reqDTO.getResumeId())
                .orElseThrow(() -> new ApiException403("스크랩 하려는 이력서를 찾을 수 없습니다."));

        Scrap scrap = scrapJPARepository.findCompScrapByResumeIdAndUserId(reqDTO.getResumeId(), sessionUser.getId())
                .orElse(null);
        if (scrap == null) {
            Scrap scrapResult = scrapJPARepository.save(reqDTO.toEntity(resume, sessionUser));
            return new ScrapResponse.CompDTO(scrapResult);
        } else {
            scrapJPARepository.deleteById(scrap.getId());
            return null;
        }
    }

    // 게스트가스크랩
    @Transactional
    public ScrapResponse.GuestDTO scrapByGuest(ScrapRequest.ScrapDTO reqDTO, User sessionUser) {
        Jobopen jobopen = jobopenJPARepository.findById(reqDTO.getJobopenId())
                .orElseThrow(() -> new ApiException403("스크랩 하려는 공고를 찾을 수 없습니다."));

        Scrap scrap = scrapJPARepository.findGuestScrapByJobopenIdAndUserId(reqDTO.getJobopenId(), sessionUser.getId())
                .orElse(null);
        if (scrap == null) {
            Scrap scrapResult = scrapJPARepository.save(reqDTO.toEntity(jobopen, sessionUser));
            return new ScrapResponse.GuestDTO(scrapResult);
        } else {
            scrapJPARepository.deleteById(scrap.getId());
            return null;
        }
    }

    // 회사가스크앱한이력서조회
    public List<ResumeResponse.ScrapDTO> scrapResumeBycomp(Integer id) {
        List<ResumeResponse.ScrapDTO> respDTO = resumeJPARepository.findByUserIdJoinScrap(id);
        return respDTO;
    }

    // 게스트가스크랩한공고조회
    public List<JobopenResponse.ScrapDTO> scrapJobopenByGuest(Integer id) {
        List<JobopenResponse.ScrapDTO> respDTO = jobopenJPARepository.findByUserIdJoinScrap(id);
        return respDTO;
    }
}
