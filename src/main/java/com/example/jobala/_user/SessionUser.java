package com.example.jobala._user;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class SessionUser {
    private Integer id;
    private String username;
    private String email;
    private Timestamp createdAt;
    private Integer role;
    private User user;

    @Builder
    public SessionUser(Integer id, String username, String email, Timestamp createdAt, Integer role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.role = role;
    }

    public SessionUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.role = user.getRole();
    }

}
