package com.study.mytodo.mapper;

import com.study.mytodo.domain.TodoVO;
import com.study.mytodo.dto.TodoDTO;

import java.util.List;

public interface TodoMapper {

    String getTime();

    void insert(TodoVO todoVO);

    List<TodoVO> selectAll();

    TodoVO selectOne(Long tno);

}
