package com.example.jobala.resume;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "resume_tb")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private String name; // 지원자 이름
    private String resumeTitle;
    private String hopeJob;
    private String career;
    private String license;
    private String content;
    private String edu;
    private LocalDateTime createdAt;
    @ColumnDefault("0")
    private Integer ROLE; // 0 -> guest, 1 -> comp
}
