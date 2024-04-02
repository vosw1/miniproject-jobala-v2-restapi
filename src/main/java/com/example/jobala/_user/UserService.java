package com.example.jobala._user;

import com.example.jobala._core.errors.apiException.ApiException400;
import com.example.jobala._core.errors.apiException.ApiException401;
import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._core.utill.Paging;
import com.example.jobala.guest.GuestRequest;
import com.example.jobala.guest.GuestResponse;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenJPARepository;
import com.example.jobala.jobopen.JobopenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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
    private final Paging paging;


    //기업,개인 - 채용공고 검색필터
    public List<JobopenResponse.ListDTO> jobopenSearch(String skills, GuestRequest.SearchDTO resDTO) {
        return userQueryRepository.findAll(skills, resDTO);
    }

    // 기업,개인 - 채용공고 목록
    public List<JobopenResponse.ListDTO> findAll() {
        return userQueryRepository.findByJoboopenAll();
    }

    //메인 공고 목록조회
    public UserResponse.MainDTO mainJobopenList(Integer page, User sessionUser) {
        List<Jobopen> jobopenList = jobopenJPARepository.main();
        return new UserResponse.MainDTO(jobopenList);
    }

    // 로그인
    public User login(UserRequest.LoginDTO reqDTO) {
        try {
            return userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                    .orElseThrow(() -> new ApiException401("인증되지 않았습니다."));

        } catch (EmptyResultDataAccessException e) {
            throw new ApiException401("아이디,비밀번호가 틀렸어요");
        }
    }

    // 회원가입
    @Transactional
    public UserResponse.JoinDTO join(UserRequest.JoinDTO reqDTO) {
        Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername());
        if (userOP.isPresent()) {
            throw new ApiException400("중복된 유저네임입니다.");
        }
        User user = userJPARepository.save(reqDTO.toCompEntity());
        return new UserResponse.JoinDTO(user);
    }

}
