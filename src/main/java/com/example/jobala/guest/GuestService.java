package com.example.jobala.guest;

import com.example.jobala._core.errors.apiException.ApiException403;

import com.example.jobala._core.utill.Paging;
import com.example.jobala._user.SessionUser;
import com.example.jobala._user.User;
import com.example.jobala._user.UserJPARepository;
import com.example.jobala._user.UserResponse;
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

    // 개인 - 프로필업데이트
    @Transactional
    public UserResponse.GuestProfile guestUpdateProfile(GuestRequest.GuestProfileUpdateDTO reqDTO, SessionUser sessionUser) {
        User user = guestJPARepository.findById(sessionUser.getId())
                .orElseThrow(() -> new ApiException403("수정할 프로필이 없습니다.")).getUser();

        byte[] decodedBytes = Base64.getDecoder().decode(reqDTO.getImgFilename().getBytes());
        String imageUUID = UUID.nameUUIDFromBytes(decodedBytes).randomUUID() +"_" + reqDTO.getImgTitle();

        Path imgPath = Paths.get("./image/" + imageUUID);

        try {
            Files.write(imgPath, decodedBytes);
            String webImgPath = imgPath.toString().replace("\\", "/");
            webImgPath = webImgPath.substring(webImgPath.lastIndexOf("/") + 1);

            user.setGuestProfileUpdateDTO(reqDTO, webImgPath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new UserResponse.GuestProfile(user);
    }

    // 개인 - 프로필관리
    public UserResponse.GuestProfile guestProgile(Integer id) {
        User user = userJPARepository.findById(id).orElseThrow(() -> new ApiException403("유저의 정보를 찾을 수 없습니다."));
        return new UserResponse.GuestProfile(user);
    }

    //이력서 페이징 하기 위한 목록 조회
    public ResumeResponse.MngDTO guestResumesMng(int page, Integer sessionUserId) {
        List<Resume> resumes = resumeJPARepository.findResumeByWithUserId(sessionUserId);
        return new ResumeResponse.MngDTO(sessionUserId, resumes);
    }
}