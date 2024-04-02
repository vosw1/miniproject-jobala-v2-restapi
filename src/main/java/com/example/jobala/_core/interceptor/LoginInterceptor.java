package com.example.jobala._core.interceptor;

import com.example.jobala._core.errors.apiException.ApiException401;
import com.example.jobala._core.errors.exception.Exception401;
import com.example.jobala._user.User;
import com.example.jobala._user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;


public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle............");
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            throw new ApiException401("로그인 하셔야 해요");
        }
        return true;
    }
}
