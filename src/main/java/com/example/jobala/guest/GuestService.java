package com.example.jobala.guest;

import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._core.utill.Paging;
import com.example.jobala._user.User;
import com.example.jobala._user.UserJPARepository;
import com.example.jobala._user.UserResponse;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeJPARepository;
import com.example.jobala.resume.ResumeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestJPARepository guestJPARepository;
    private final GuestQueryRepository guestQueryRepository;
    private final UserJPARepository userJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    private final Paging paging;

    // 프로필업데이트
    @Transactional
    public UserResponse.CompProfile guestUpdateProfile(GuestRequest.GuestProfileUpdateDTO reqDTO, User sessionUser) {
        User user = guestJPARepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("수정할 프로필이 없습니다.")).getUser();

//        System.out.println("reqDTO = " + reqDTO.getImgFilename());
//        MultipartFile imgFilename = reqDTO.getImgFilename();
//
//        // 이미지 파일의 저장 경로 설정
//        String GuestImgFilename = UUID.randomUUID() + "_" + imgFilename.getOriginalFilename();
//        Path imgPath = Paths.get("./image/" + GuestImgFilename);
//        try {
//            Files.write(imgPath, imgFilename.getBytes());
//            String webImgPath = imgPath.toString().replace("\\", "/");
//            webImgPath = webImgPath.substring(webImgPath.lastIndexOf("/") + 1);
//
            user.setGuestProfileUpdateDTO(reqDTO);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return new UserResponse.CompProfile(user);
    }

    //기업,개인 - 채용공고 검색필터
    public List<JobopenResponse.ListDTO> jobopenSearch(String skills, GuestResponse.SearchDTO resDTO) {
        return guestQueryRepository.findAll(skills, resDTO);
    }

    // 기업,개인 - 채용공고 목록
    public List<JobopenResponse.ListDTO> findAll() {
        return guestQueryRepository.findByJoboopenAll();
    }

    // 개인 - 프로필관리
    public UserResponse.GuestProfile guestProgile(Integer id) {
        User user = userJPARepository.findById(id).orElseThrow(() -> new Exception404("유저의 정보를 찾을 수 없습니다."));
        return new UserResponse.GuestProfile(user);
    }

    //이력서 페이징 하기 위한 목록 조회
    public ResumeResponse.MngDTO resumesFindAll(int page, Integer sessionUserId) {
        List<Resume> resumes = guestJPARepository.findAll();
        return new ResumeResponse.MngDTO(sessionUserId, resumes);
    }
}