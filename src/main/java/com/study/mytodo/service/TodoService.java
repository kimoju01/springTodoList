package com.study.mytodo.service;

import com.study.mytodo.dto.PageRequestDTO;
import com.study.mytodo.dto.PageResponseDTO;
import com.study.mytodo.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    void register(TodoDTO todoDTO);

    // 페이징 없이 목록 가져오기
//    List<TodoDTO> getAll();

    // 페이징 처리 목록 가져오기
    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    TodoDTO getOne(Long tno);

    void remove(Long tno);

    void modify(TodoDTO todoDTO);

}
