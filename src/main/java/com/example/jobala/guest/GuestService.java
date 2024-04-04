package com.example.jobala.guest;

import com.example.jobala._core.errors.apiException.ApiException400;
import com.example.jobala._core.errors.apiException.ApiException404;
import com.example.jobala._core.utill.Paging;
import com.example.jobala._core.utill.UpdateProfileUtil;
import com.example.jobala._user.*;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeJPARepository;
import com.example.jobala.resume.ResumeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestJPARepository guestJPARepository;
    private final UserJPARepository userJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    private final Paging paging;
    private final UpdateProfileUtil updateProfileUtil;

    // 개인 - 프로필업데이트
    @Transactional
    public UserResponse.GuestProfile guestUpdateProfile(UserRequest.UserUpdateDTO reqDTO, SessionUser sessionUser) {
        User user = guestJPARepository.findById(sessionUser.getId())
                .orElseThrow(() -> new ApiException404("수정할 프로필이 없습니다.")).getUser();

        try {
            String imgFilename = reqDTO.getImgFilename(); // 이미지 파일 이름 가져오기
            String imgTitle = reqDTO.getImgTitle(); // 이미지 타이틀 가져오기
            updateProfileUtil.fileUpdate(imgFilename, imgTitle); // 파일 업데이트 수행
        } catch (IOException e) {
            throw new ApiException400("올바른 저장 경로를 찾지 못했습니다.");
        }
        return new UserResponse.GuestProfile(user);
    }

    // 개인 - 프로필관리
    public UserResponse.GuestProfile guestProgile(SessionUser sessionUser) {
        User user = userJPARepository.findById(sessionUser.getId()).orElseThrow(() -> new ApiException404("유저의 정보를 찾을 수 없습니다."));
        return new UserResponse.GuestProfile(user);
    }

    //개인 - 이력서 리스트
    public ResumeResponse.MngDTO guestResumesMng(int page, SessionUser sessionUser) {
        List<Resume> resumes = resumeJPARepository.findByUserId(sessionUser.getId());
        return new ResumeResponse.MngDTO(sessionUser.getId(), resumes);
    }
}