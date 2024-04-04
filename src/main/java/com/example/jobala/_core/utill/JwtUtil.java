package com.example.jobala._core.utill;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jobala._user.SessionUser;
import com.example.jobala._user.User;

import java.util.Date;

public class JwtUtil {
    public static String create(User user) {
        String jwt = JWT.create()
                .withSubject("blog")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("role", user.getRole())
                .sign(Algorithm.HMAC512("jobala"));

        return jwt;
    }

    public static SessionUser verify(String jwt) {
        System.out.println(jwt);

        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("jobala")).build().verify(jwt);
        int id = decodedJWT.getClaim("id").asInt();
        String username = decodedJWT.getClaim("username").asString();
        int role = decodedJWT.getClaim("role").asInt();

        return SessionUser.builder()
                .id(id)
                .username(username)
                .role(role)
                .build();
    }
}
