package com.example.jobala._core.utils;

import com.example.jobala._core.utill.JwtUtil;
import com.example.jobala._user.User;
import org.junit.jupiter.api.Test;

public class JwtUtilTest {

    @Test
    public void create_test(){
        // given
        User user = User.builder()
                .id(1)
                .username("cos1")
                .build();
        // when
        String jwt = JwtUtil.create(user);
        System.out.println(jwt);
    }
}

