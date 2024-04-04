package com.example.jobala.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyJPARepository extends JpaRepository<Reply, Integer> {

    // 글 상세보기
    @Query("select r from Reply r join fetch r.user u where r.board.id = :boardId")
    List<Reply> findByUserId(@Param("boardId") Integer boardId);
}
