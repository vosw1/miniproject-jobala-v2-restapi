package com.example.jobala._junit;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jobala._core.utill.JwtUtil;
import com.example.jobala._user.SessionUser;
import com.example.jobala._user.User;
import com.example.jobala.jobopen.JobopenRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import java.util.Date;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = {TestConfig.class})
public class JobopenControllerTest {




    @Autowired
    private MockMvc mvc;

    private MockHttpSession mockSession;

    @Autowired
    private ObjectMapper om;
    @Autowired
    private EntityManager em;

    public String jwt() {
        String jwt = JWT
                .create()
                .withSubject("blog")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .withClaim("id", 12)
                .withClaim("username", "com1")
                .withClaim("role", 1)
                .sign(Algorithm.HMAC512("jobala"));
        return jwt;
    }



    // 공고 삭제
    @Test
    public void delete_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions resultActions = mvc.perform(delete("/api/comp/jobopens/" + id) // 주소 수정 필요
                .session(mockSession));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("delete_test : " + responseBody);
//        em.flush();
//        // then

        resultActions.andExpect(jsonPath("$.body").doesNotExist());
        resultActions.andExpect(status().isOk());
    }

    // 공고 수정
    @Test
    void update_test() throws Exception {
        // given
        int id = 1;
        JobopenRequest.UpdateDTO updateDTO = new JobopenRequest.UpdateDTO(
                "공고 제목", // jobopenTitle
                "경력", // career
                "대학교 졸업", // edu
                "계약직", // jobType
                "협의", // salary
                "프론트엔드", // hopeJob
                "부산", // compLocation
                java.sql.Date.valueOf("2024-11-30"), // endTime
                Collections.singletonList("java") // skills
        );

        // when
        String requestBody = om.writeValueAsString(updateDTO);

        ResultActions resultActions = mvc.perform(put("/api/comp/jobopens" + id)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .session(mockSession));

        System.out.println("테스트 : " + requestBody);
        // then
        resultActions.andExpect(status().is2xxSuccessful());
    }

    // 공고 등록
    @Test
    public void jobopenSave_test() throws Exception {
        // given
        JobopenRequest.SaveDTO saveDTO = new JobopenRequest.SaveDTO(
                "대학교 졸업", // edu
                "공고 제목", // jobopenTitle
                "경력", // career
                "계약직", // jobType
                "협의", // salary
                "프론트엔드", // hopeJob
                "부산", // compLocation
                java.sql.Date.valueOf("2024-11-30"), // endTime
                Collections.singletonList("java") // skills
        );

        // when
        String requestBody = om.writeValueAsString(saveDTO);

        ResultActions resultActions = mvc
                .perform(post("/api/comp/jobopens") // 주소 수정 필요
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(mockSession));
        System.out.println("테스트 : " + requestBody);

        // then
        resultActions.andExpect(status().is2xxSuccessful());

    }

    //@BeforeEach // Test메서드 실행 직전마다 호출된다
    public void setUp() {
        // 세션 생성하기
        SessionUser sessionUser = SessionUser.builder()
                .id(12)
                .username("com1")
                .role(1)
                .build();

        mockSession = new MockHttpSession();
        mockSession.setAttribute("sessionUser", sessionUser);
    }

    //공고 보기
    @Test
    void detailForm_jwt_test() throws Exception {
        // given
        User user = User.builder()
                .id(1)
                .username("cos1")
                .role(0)
                .build();
        // when
        String jwt = JwtUtil.create(user);


        int id = 1;

        ResultActions resultActions = mvc
                .perform(get("/api/jobopens/" + id).header("Authorization", "Bearer "+jwt));


        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // then
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(status().isOk());


//                .andExpect(result -> )
//                   .andExpect(jsonPath("$.msg").value("성공")); // 응답 메시지가 "성공"인지 확인
//                .andExpect(jsonPath("$.body").exists()) // 응답 본문이 존재하는지 확인
//                .andExpect(jsonPath("$.body.id").value(id)) // 응답 본문에 공고의 ID가 있는지 및 값이 올바른지 확인
//                .andExpect(jsonPath("$.body.title").exists()) // 공고의 제목이 응답 본문에 존재하는지 확인
//                .andExpect(jsonPath("$.body.description").exists());
    }

    @Test
    void detailForm_session_test() throws Exception {
        // given
        int id = 1;

        ResultActions resultActions = mvc
                .perform(get("/api/jobopens/" + id).session(mockSession));


        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // then
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("성공")) // 응답 메시지가 "성공"인지 확인
                .andExpect(jsonPath("$.body").exists()) // 응답 본문이 존재하는지 확인
                .andExpect(jsonPath("$.body.id").value(id)); // 응답 본문에 공고의 ID가 있는지 및 값이 올바른지 확인

    }
}