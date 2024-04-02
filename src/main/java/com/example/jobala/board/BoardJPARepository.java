package com.example.jobala.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {
    // TODO: 이름
    @Query("select b from Board b join fetch b.user u where b.id = :id ")
    Optional<Board> findByIdJoinUser(@Param("id")int id);

    // TODO: 이름
    @Query("select new com.example.jobala.board.BoardResponse$BoardDTO(b) from Board b order by b.id DESC")
    List<BoardResponse.BoardDTO> findBoardAll();

    //페이징
    Page<Board> findAll(Pageable pageable);
}
