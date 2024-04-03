package com.example.jobala._junit;

import com.example.jobala.jobopen.JobopenController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@WebMvcTest(controllers = JobopenController.class)
public class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class AdditionalConfig {
        @Bean
        public WebMvcConfigurer webMvcConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addInterceptors(InterceptorRegistry registry) {
                    // 인터셉터를 제외하거나 필요한 인터셉터만 추가합니다.
                }
            };
        }
    }
}

