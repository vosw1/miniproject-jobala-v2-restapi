package com.example.jobala.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {

    // 글 상세보기
    @Query("select b from Board b join fetch b.user u where b.id = :id ")
    Optional<Board> findByIdJoinUser(@Param("id") int id);

    // 글 목록
    @Query("select new com.example.jobala.board.BoardResponse$DTO(b) from Board b order by b.id DESC")
    List<BoardResponse.DTO> findBoardAll();
}
