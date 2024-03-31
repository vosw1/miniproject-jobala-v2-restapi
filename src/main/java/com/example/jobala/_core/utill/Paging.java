package com.example.jobala._core.utill;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

@Controller
public class Paging {

    public Pageable guestPaging(int page) {
        return PageRequest.of(page, 3, Sort.by(Sort.Direction.DESC, "id"));
    }

    public Pageable compPaging(int page) {
        return PageRequest.of(page, 3, Sort.by(Sort.Direction.DESC, "id"));
    }

    public Pageable boardPaging(int page) {
        return PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "id"));
    }

    public Pageable mainPaging(int page) {
        return PageRequest.of(page, 9, Sort.by(Sort.Direction.DESC, "id"));
    }
    
//TODO: 페이지 번호를 반환하는 기능 & get
//    public int boardPaging(int page) getPageNumber를 DTO에 담아서 주면 될듯 하다.
//        return PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "id")).getPageNumber();
//    }
}