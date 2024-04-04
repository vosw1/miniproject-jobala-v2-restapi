package com.example.jobala._user;

import com.example.jobala._core.errors.apiException.ApiException400;
import com.example.jobala._core.errors.apiException.ApiException401;
import com.example.jobala._core.utill.JwtUtil;
import com.example.jobala.guest.GuestRequest;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenJPARepository;
import com.example.jobala.jobopen.JobopenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JobopenJPARepository jobopenJPARepository;
    private final UserJPARepository userJPARepository;
    private final UserQueryRepository userQueryRepository;

    //기업,개인 - 채용공고 검색필터
    public List<JobopenResponse.ListDTO> jobopenSearch(String skills, GuestRequest.SearchDTO resDTO) {
        return userQueryRepository.findAll(skills, resDTO);
    }

    //메인 공고 목록조회
    public UserResponse.MainDTO mainJobopenList() {
        List<Jobopen> jobopenList = jobopenJPARepository.main();
        return new UserResponse.MainDTO(jobopenList);
    }

    // 로그인
    public String login(UserRequest.LoginDTO reqDTO) {
        User user = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new ApiException401("아이디, 비밀번호가 틀렸어요"));
        String jwt = JwtUtil.create(user);
        return jwt;
    }

    // 개인 회원가입
    @Transactional
    public UserResponse.GuestDTO joinGuest(UserRequest.GuestJoinDTO reqDTO) {
        Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername());
        if (userOP.isPresent()) {
            throw new ApiException400("중복된 유저네임입니다.");
        }
        User user = userJPARepository.save(reqDTO.toEntity());
        return new UserResponse.GuestDTO(user);
    }

    // 기업 회원가입
    @Transactional
    public UserResponse.CompDTO joinComp(UserRequest.CompJoinDTO reqDTO) {
        Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername());
        if (userOP.isPresent()) {
            throw new ApiException400("중복된 유저네임입니다.");
        }
        User user = userJPARepository.save(reqDTO.toEntity());
        return new UserResponse.CompDTO(user);
    }

}
