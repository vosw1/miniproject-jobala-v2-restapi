package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import com.example.jobala.guest.GuestResponse;
import com.example.jobala.resume.Resume;
import com.google.gson.Gson;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobopenQueryRepository {
    private final EntityManager em;

}
