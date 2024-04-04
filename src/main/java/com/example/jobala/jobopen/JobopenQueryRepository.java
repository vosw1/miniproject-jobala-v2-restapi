package com.example.jobala.jobopen;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JobopenQueryRepository {
    private final EntityManager em;

}
