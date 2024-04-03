package com.example.jobala._junit;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jobala._user.SessionUser;
import com.example.jobala.jobopen.JobopenRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;
import java.util.Date;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class JobopenControllerTest {
    @Autowired
    private MockMvc mvc;

    private MockHttpSession mockSession;

    @Autowired
    private ObjectMapper om;

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

    @BeforeEach // Test메서드 실행 직전마다 호출된다
    public void setUp() {
        // 세션 생성하기
        SessionUser sessionUser = SessionUser.builder()
                .id(12)
                .username("com1")
                .role(1)
                .build();

        mockSession = new MockHttpSession();
        mockSession.setAttribute("user", sessionUser);
    }

    // 공고 삭제
    @Test
    public void delete_test() throws Exception{
        // given
        int id = 1;

        // when
        ResultActions resultActions = mvc.perform(delete("/api/comp/jobopens/" + id) // 주소 수정 필요
                .session(mockSession));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("delete_test : " + responseBody);

        // then
//        resultActions.andExpect(jsonPath("$.body").value(null));
//        resultActions.andExpect(status().isOk());
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

    //공고 보기
    @Test
    void detailForm_test() throws Exception {
        // given
        int id = 12;
        // when
        ResultActions resultActions = mvc
                .perform(get("/api/comp/jobopens"+ id).session(mockSession));
        // then
//        resultActions.andExpect(jsonPath("$.body").exists());
//        resultActions.andExpect(jsonPath("$.msg").value("성공"));

    }
}