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
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    private final GuestQueryRepository guestQueryRepository;
    private final UserJPARepository userJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    private final Paging paging;

    // 프로필업데이트
    @Transactional
    public UserResponse.GuestProfile guestUpdateProfile(GuestRequest.GuestProfileUpdateDTO reqDTO, User sessionUser) {
        User user = guestJPARepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("수정할 프로필이 없습니다.")).getUser();

        //베이스 64로 들어오는 문자열을 바이트로 디코딩하기
        byte[] decodedBytes = Base64.getDecoder().decode(reqDTO.getImgFilename().getBytes());
        String imageUUID = UUID.nameUUIDFromBytes(decodedBytes).randomUUID() + ".png";

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
        User user = userJPARepository.findById(id).orElseThrow(() -> new Exception404("유저의 정보를 찾을 수 없습니다."));
        return new UserResponse.GuestProfile(user);
    }

    //이력서 페이징 하기 위한 목록 조회
    public ResumeResponse.MngDTO guestResumesMng(int page, Integer sessionUserId) {
        List<Resume> resumes = resumeJPARepository.findResumeByWithUserId(sessionUserId);
        return new ResumeResponse.MngDTO(sessionUserId, resumes);
    }
}